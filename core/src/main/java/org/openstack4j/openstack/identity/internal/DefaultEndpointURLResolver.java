package org.openstack4j.openstack.identity.internal;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.openstack4j.api.exceptions.RegionEndpointNotFoundException;
import org.openstack4j.api.identity.EndpointURLResolver;
import org.openstack4j.api.types.Facing;
import org.openstack4j.api.types.ServiceType;
import org.openstack4j.model.identity.AuthVersion;
import org.openstack4j.model.identity.Endpoint;
import org.openstack4j.model.identity.Service;
import org.openstack4j.model.identity.Token;
import org.openstack4j.model.identity.URLResolverParams;

import com.google.common.base.Optional;

/**
 * Resolves an Endpoint URL based on the Service Type and Facing perspective
 *
 * @author Jeremy Unruh
 */
public class DefaultEndpointURLResolver implements EndpointURLResolver {

    private static final Map<Key, String> CACHE = new ConcurrentHashMap<Key, String>();

    @Override
    public String findURL(URLResolverParams p) {

        if (p.type == null)
            return p.token.getEndpoint();
        Key key = Key.of(p.token.getCacheIdentifier(), p.type, p.perspective, p.region);

        String url = CACHE.get(key);

        if (url != null)
            return url;

        url = resolve(p);

        if (url != null)
        {
            if (p.token.getVersion() == AuthVersion.V3) {
                CACHE.put(key, url);
            }
            return url;
        }
        else if (p.region != null)
            throw RegionEndpointNotFoundException.create(p.region, p.type.getServiceName());

        return p.token.getEndpoint();
    }

    private String resolve(URLResolverParams p) {

        Token token = p.token;

        for (Service service : token.getCatalog()) {
            if (p.type == ServiceType.forName(service.getType()))
            {
                if(p.perspective == null) {
                    p.perspective = Facing.PUBLIC;
                }

                for (Endpoint ep : service.getEndpoints()) {

                    if(matches(ep, p))
                        return ep.getUrl().toString();
                }
            }
        }
        return null;
    }

    /**
     * Returns <code>true</code> for any endpoint that matches a given
     * {@link URLResolverParams}.
     *
     * @param endpoint
     * @param p
     * @return
     */
    private boolean matches(Endpoint endpoint, URLResolverParams p) {
        boolean matches = endpoint.getIface() == p.perspective;
        if (Optional.fromNullable(p.region).isPresent()) {
            matches &= endpoint.getRegion().equals(p.region);
        }
        return matches;
    }

    private static final class Key {
        private final String uid;
        private final ServiceType type;
        private final Facing perspective;

        private Key(String uid, ServiceType type, Facing perspective) {
            this.type = type;
            this.perspective = perspective;
            this.uid = uid;
        }

        static Key of(String uid, ServiceType type, Facing perspective, String region) {
            return new Key((region == null) ? uid : uid+region, type, perspective);
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result
                    + ((perspective == null) ? 0 : perspective.hashCode());
            result = prime * result + ((type == null) ? 0 : type.hashCode());
            result = prime * result + ((uid == null) ? 0 : uid.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Key other = (Key) obj;
            if (perspective != other.perspective)
                return false;
            if (type != other.type)
                return false;
            if (uid == null) {
                if (other.uid != null)
                    return false;
            } else if (!uid.equals(other.uid))
                return false;
            return true;
        }
    }


}