package org.openstack4j.connectors.resteasy;

import javax.ws.rs.ext.ContextResolver;

import org.jboss.resteasy.plugins.providers.InputStreamProvider;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.openstack4j.core.transport.ObjectMapperSingleton;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

/**
 * Rest Easy provider setup to play nice with Jackson
 * 
 * @author Jeremy Unruh
 */
public class ResteasyClientFactory extends ResteasyProviderFactory {

    private static final ResteasyClientFactory INSTANCE = new ResteasyClientFactory();

    private JacksonJsonProvider jsonProvider;
    private InputStreamProvider streamProvider;

    public ResteasyClientFactory() {
        super();
        
        addContextResolver(new CustomContextResolver());
        
        jsonProvider = new JacksonJsonProvider();
        addMessageBodyReader(jsonProvider);
        addMessageBodyWriter(jsonProvider);

        streamProvider = new InputStreamProvider();
        addMessageBodyReader(streamProvider);
        addMessageBodyWriter(streamProvider);
    }

    public static ResteasyClientFactory getInstance() {
        return INSTANCE;
    }
    

    private static final class CustomContextResolver implements ContextResolver<ObjectMapper> {

        /**
         * {@inheritDoc}
         */
        @Override
        public ObjectMapper getContext(Class<?> type) {
            return ObjectMapperSingleton.getContext(type);
        }
    }

}
