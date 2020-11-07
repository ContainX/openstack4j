package org.openstack4j.model.identity.v2.builder;

/**
 * The Identity V2 builders
 */
public interface IdentityV2Builders {

    /**
     * The builder to create an Endpoint.
     *
     * @return the endpoint builder
     */
    EndpointBuilder endpoint();

    /**
     * The builder to create a Role.
     *
     * @return the role builder
     */
    RoleBuilder role();

    /**
     * The builder to create a service.
     *
     * @return the service builder
     */
    ServiceBuilder service();

    /**
     * The builder to create a Service Endpoint.
     *
     * @return the service endpoint builder
     */
    ServiceEndpointBuilder serviceEndpoint();

    /**
     * The builder to create a tenant
     *
     * @return the tenant builder
     */
    TenantBuilder tenant();

    /**
     * The builder to create a user
     *
     * @return the user builder
     */
    UserBuilder user();

}
