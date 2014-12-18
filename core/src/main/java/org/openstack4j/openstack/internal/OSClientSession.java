package org.openstack4j.openstack.internal;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

import org.openstack4j.api.Apis;
import org.openstack4j.api.EndpointTokenProvider;
import org.openstack4j.api.OSClient;
import org.openstack4j.api.compute.ComputeService;
import org.openstack4j.api.heat.HeatService;
import org.openstack4j.api.identity.EndpointURLResolver;
import org.openstack4j.api.identity.IdentityService;
import org.openstack4j.api.image.ImageService;
import org.openstack4j.api.networking.NetworkingService;
import org.openstack4j.api.storage.BlockStorageService;
import org.openstack4j.api.storage.ObjectStorageService;
import org.openstack4j.api.telemetry.TelemetryService;
import org.openstack4j.api.types.Facing;
import org.openstack4j.api.types.ServiceType;
import org.openstack4j.core.transport.Config;
import org.openstack4j.model.identity.Access;
import org.openstack4j.model.identity.Token;
import org.openstack4j.model.identity.URLResolverParams;
import org.openstack4j.openstack.identity.functions.ServiceToServiceType;
import org.openstack4j.openstack.identity.internal.DefaultEndpointURLResolver;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * A client which has been identified.  Any calls spawned from this session will automatically utilize the original authentication that was
 * successfully validated and authorized
 * 
 * @author Jeremy Unruh
 */
public class OSClientSession implements OSClient, EndpointTokenProvider {

    private static final ThreadLocal<OSClientSession> sessions = new ThreadLocal<OSClientSession>();

    EndpointURLResolver epr = new DefaultEndpointURLResolver();
    Config config;
    Access access;
    Facing perspective;
    String region;
    Set<ServiceType> supports;

    private OSClientSession(Access access, String endpoint, Facing perspective, Config config)
    {
        this.access = access;
        this.config = config;
        this.perspective = perspective;
        sessions.set(this);
    }

    private OSClientSession(OSClientSession parent, String region)
    {
        this.access = parent.access;
        this.perspective = parent.perspective;
        this.region = region;
    }

    public static OSClientSession createSession(Access access) {
        return new OSClientSession(access, access.getEndpoint(), null, null);
    }

    public static OSClientSession createSession(Access access, Facing perspective, Config config) {
        return new OSClientSession(access, access.getEndpoint(), perspective, config);
    }

    public static OSClientSession getCurrent() {
        return sessions.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OSClient useRegion(String region) {
        this.region = region;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OSClient removeRegion() {
        return useRegion(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<ServiceType> getSupportedServices() {
        if (supports == null)
            supports = Sets.immutableEnumSet(Iterables.transform(access.getServiceCatalog(), new ServiceToServiceType()));
        return supports;
    }

    /**
     * @return the current perspective
     */
    public Facing getPerspective() {
        return perspective;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supportsCompute() {
        return supports.contains(ServiceType.COMPUTE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supportsIdentity() {
        return supports.contains(ServiceType.IDENTITY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supportsNetwork() {
        return supports.contains(ServiceType.NETWORK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supportsImage() {
        return supports.contains(ServiceType.IMAGE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supportsHeat() {
        return supports.contains(ServiceType.ORCHESTRATION);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supportsBlockStorage() {
        return supports.contains(ServiceType.BLOCK_STORAGE);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supportsObjectStorage() {
        return supports.contains(ServiceType.OBJECT_STORAGE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Token getToken() {
        return access.getToken();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getEndpoint() {
        return access.getEndpoint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getEndpoint(ServiceType service) {
        return addNATIfApplicable(epr.findURL(URLResolverParams.create(access, service).perspective(perspective).region(region)));
    }
    
    private String addNATIfApplicable(String url) {
        if (config != null && config.isBehindNAT()) {
            try {
                URI uri = new URI(url);
                return url.replace(uri.getHost(), config.getEndpointNATResolution());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        return url;
    }
    
    /**
     * @return the original client configuration associated with this session
     */
    public Config getConfig()
    {
        return config;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getTokenId() {
        return getToken().getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IdentityService identity() {
        return Apis.getIdentityServices();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ComputeService compute() {
        return Apis.getComputeServices();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NetworkingService networking() {
        return Apis.getNetworkingServices();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImageService images() {
        return Apis.getImageService();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Access getAccess() {
        return access;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BlockStorageService blockStorage() {
        return Apis.get(BlockStorageService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TelemetryService telemetry() {
        return Apis.get(TelemetryService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HeatService heat() {
        return Apis.getHeatServices();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObjectStorageService objectStorage() {
        return Apis.get(ObjectStorageService.class);
    }
}
