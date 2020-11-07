package org.openstack4j.model.identity.v3.builder;

/**
 * The Identity V3 builders
 */
public interface IdentityV3Builders {

    /**
     * The builder to create a Credential.
     *
     * @return the credential builder
     */
    CredentialBuilder credential();

    /**
     * The builder to create a Domain.
     *
     * @return the domain builder
     */
    DomainBuilder domain();

    /**
     * The builder to create a Endpoint.
     *
     * @return the endpoint builder
     */
    EndpointBuilder endpoint();

    /**
     * The builder to create a Group.
     *
     * @return the group builder
     */
    GroupBuilder group();

    /**
     * The builder to create a Policy.
     *
     * @return the policy builder
     */
    PolicyBuilder policy();

    /**
     * The builder to create a Project.
     *
     * @return the project builder
     */
    ProjectBuilder project();

    /**
     * The builder to create a Region.
     *
     * @return the region builder
     */
    RegionBuilder region();

    /**
     * The builder to create a Role.
     *
     * @return the role builder
     */
    RoleBuilder role();

    /**
     * The builder to create a Service.
     *
     * @return the service builder
     */
    ServiceBuilder service();

    /**
     * The builder to create a User.
     *
     * @return the user builder
     */
    UserBuilder user();

}
