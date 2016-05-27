package org.openstack4j.api

import com.google.common.collect.ImmutableMap
import groovy.util.logging.Slf4j

import org.junit.Rule;
import org.junit.rules.TestName;
import org.openstack4j.api.Builders
import org.openstack4j.model.identity.v3.*
import org.openstack4j.model.identity.v2.Tenant
import org.openstack4j.model.manila.Share
import org.openstack4j.model.manila.Share.Protocol
import org.openstack4j.model.manila.ShareCreate
import org.openstack4j.model.storage.block.Volume
import org.openstack4j.model.compute.Flavor
import org.openstack4j.openstack.heat.domain.HeatStackCreate
import org.openstack4j.model.sahara.Cluster
import spock.lang.Specification


@Slf4j
class BuilderSpec extends Specification {

    @Rule
    TestName BuilderTest

    def static final String TENANT_NAME = "newTenant"
    def static final String TENANT_DESCRIPTION = "test identity v2 tenant builder"
    def static final boolean TENANT_ENABLED = true
    def static final String USER_NAME = "newUser"
    def static final String USER_EMAIL = "newUser@os4j.com"
    def static final String USER_TENANT_ID = "validId"
    def static final String USER_DESCRIPTION = "test identity user builder"
    def static final String USER_DOMAIN_ID = "default"
    def static final boolean USER_ENABLED = true
    def static final String ROLE_NAME = "newRole"
    def static final String ROLE_TENANT_ID = "validId"
    def static final String ROLE_DESCRIPTION = "test identity v2 role builder"
    def static final boolean ROLE_ENABLED = true
    def static final String SERVICE_NAME = "newService"
    def static final String SERVICE_DESCRIPTION = "test identity v2 service builder"
    def static final String SERVICE_TYPE = "identity"
    def static final String ENDPOINT_ADMIN_URL = "http://127.0.0.1:8774/v2.1/b80f8d4e28b74188858b654cb1fccf7d"
    def static final String ENDPOINT_INTERNAL_URL = "http://127.0.0.1:8774/v2.1/b80f8d4e28b74188858b654cb1fccf7d"
    def static final String ENDPOINT_PUBLIC_URL = "http://127.0.0.1:8774/v2.1/b80f8d4e28b74188858b654cb1fccf7d"
    def static final String ENDPOINT_REGION = "RegionOne"
    def static final String ENDPOINT_SERVICE_ID = "validId"
    def static final String FLAVOR_NAME = "Large Resources Template"
    def static final Integer FLAVOR_RAM = 4096
    def static final Integer FLAVOR_VCPUS = 6
    def static final Integer FLAVOR_DISK = 120
    def static final float FLAVOR_RXTXFACTOR = 1.2f
    def static final String VOLUME_NAME = "Ubuntu 12.04 LTS"
    def static final String VOLUME_DESCRIPTION = "test block storage builder"
    def static final String VOLUME_IMAGEREF = "imageId"
    def static final String STACK_NAME = "newStack"
    def static final String STACK_TEMPLATE = "template_url"
    def static final ImmutableMap<String, String> STACK_PARAMS = ImmutableMap.of("param", "bogus");
    def static final String SHARE_DESCRIPTION = "My third share"
    def static final Protocol SHARE_PROTOCOL = Share.Protocol.NFS
    def static final String SHARE_NAME = "my_first_share"
    def static final String SHARE_NETWORK_ID = "b1fb4828-93a2-4bbe-b388-7c9ccd69c17a"
    def static final boolean SHARE_ISPUBLIC = true
    def static final int SHARE_SIZE = 1
    def static final String CLUSTER_NAME = "my_cluster"
    def static final String CLUSTER_PLUGIN_NAME = "vanilla"
    def static final String CLUSTER_HADOOP_VERSION = "2.7.0"
    def static final String CLUSTER_TEMPLATE_ID = "someValidId"
    def static final String CLUSTER_IMAGE_ID = "validImageId"
    def static final String CLUSTER_KEYPAIR_NAME = "myKeyPair"
    def static final String CLUSTER_MANAGEMENT_NETWORK_ID = "networkId"

    def setup() {
        log.info("-> Test: '$BuilderTest.methodName'")
    }

    // ------------ Builder Tests ------------


    def "test identity v3 builders"() {

        when: "building a User"
        User userV3 = Builders.user()
                .name(USER_NAME)
                .description(USER_DESCRIPTION)
                .domainId(USER_DOMAIN_ID)
                .enabled(USER_ENABLED)
                .build()

        then: "check the user"
        userV3.getName() == USER_NAME
        userV3.getDescription() == USER_DESCRIPTION
        userV3.getDomainId() == USER_DOMAIN_ID
        userV3.isEnabled() == USER_ENABLED

        when: "building a User the other way"
        User v3User = Builders.identityV3().user()
                .name(USER_NAME)
                .description(USER_DESCRIPTION)
                .domainId(USER_DOMAIN_ID)
                .enabled(USER_ENABLED)
                .build()

        then: "check the user"
        v3User.getName() == USER_NAME
        v3User.getDescription() == USER_DESCRIPTION
        v3User.getDomainId() == USER_DOMAIN_ID
        v3User.isEnabled() == USER_ENABLED

    }


    def "test identity v2 builders"() {

        when: "building a Tenant"
        Tenant tenant = Builders.identityV2().tenant()
                .name(TENANT_NAME)
                .description(TENANT_DESCRIPTION)
                .enabled(TENANT_ENABLED)
                .build()

        then: "check the tenant"
        tenant.getName() == TENANT_NAME
        tenant.getDescription() == TENANT_DESCRIPTION
        tenant.isEnabled() == TENANT_ENABLED

        when: "building a User"
        org.openstack4j.model.identity.v2.User userV2 = Builders.identityV2().user()
                .name(USER_NAME)
                .email(USER_EMAIL)
                .tenantId(USER_TENANT_ID)
                .enabled(USER_ENABLED)
                .build();

        then: "check the user"
        userV2.getName() == USER_NAME
        userV2.getEmail() == USER_EMAIL
        userV2.getTenantId() == USER_TENANT_ID
        userV2.isEnabled() == USER_ENABLED

        when: "building a Role"
        org.openstack4j.model.identity.v2.Role role = Builders.identityV2().role()
                .name(ROLE_NAME)
                .description(ROLE_DESCRIPTION)
                .tenantId(ROLE_TENANT_ID)
                .enabled(ROLE_ENABLED)
                .build()

        then: "check the role"
        role.getName() == ROLE_NAME
        role.getDescription() == ROLE_DESCRIPTION
        role.getTenantId() == ROLE_TENANT_ID
        role.isEnabled() == ROLE_ENABLED

        when: "building a Service"
        org.openstack4j.model.identity.v2.Service service = Builders.identityV2().service()
                .name(SERVICE_NAME)
                .description(SERVICE_DESCRIPTION)
                .type(SERVICE_TYPE)
                .build()

        then: "check the service"
        service.getName() == SERVICE_NAME
        service.getDescription() == SERVICE_DESCRIPTION
        service.getType() == SERVICE_TYPE

        when: "building a Service Endpoint"
        org.openstack4j.model.identity.v2.ServiceEndpoint serviceEndpoint = Builders.identityV2().serviceEndpoint()
                .adminURL(ENDPOINT_ADMIN_URL)
                .internalURL(ENDPOINT_INTERNAL_URL)
                .publicURL(ENDPOINT_PUBLIC_URL)
                .region(ENDPOINT_REGION)
                .serviceId(ENDPOINT_SERVICE_ID)
                .build()

        then: "check the endpoint"
        serviceEndpoint.getAdminURL() == new URI(ENDPOINT_ADMIN_URL)
        serviceEndpoint.getInternalURL() == new URI(ENDPOINT_INTERNAL_URL)
        serviceEndpoint.getPublicURL() == new URI(ENDPOINT_PUBLIC_URL)
        serviceEndpoint.getRegion() == ENDPOINT_REGION
        serviceEndpoint.getServiceId() == ENDPOINT_SERVICE_ID
    }

    def "test compute builders"() {

        when: "building a Flavor"
        Flavor flavor = Builders.flavor()
                .name(FLAVOR_NAME)
                .ram(FLAVOR_RAM)
                .vcpus(FLAVOR_VCPUS)
                .disk(FLAVOR_DISK)
                .rxtxFactor(FLAVOR_RXTXFACTOR)
                .build();

        then: "check the flavor"
        flavor.getName() == FLAVOR_NAME
        flavor.getRam() == FLAVOR_RAM
        flavor.getVcpus() == FLAVOR_VCPUS
        flavor.getDisk() == FLAVOR_DISK
        flavor.getRxtxFactor() == FLAVOR_RXTXFACTOR

        when: "building a Flavor the other way"
        Flavor anotherFlavor = Builders.compute().flavor()
                .name(FLAVOR_NAME)
                .ram(FLAVOR_RAM)
                .vcpus(FLAVOR_VCPUS)
                .disk(FLAVOR_DISK)
                .rxtxFactor(FLAVOR_RXTXFACTOR)
                .build();

        then: "check the flavor"
        anotherFlavor.getName() == FLAVOR_NAME
        anotherFlavor.getRam() == FLAVOR_RAM
        anotherFlavor.getVcpus() == FLAVOR_VCPUS
        anotherFlavor.getDisk() == FLAVOR_DISK
        anotherFlavor.getRxtxFactor() == FLAVOR_RXTXFACTOR

    }

    def "test storage builders"() {

        when: "building a Volume"
        Volume volume = Builders.volume()
                .name(VOLUME_NAME)
                .description(VOLUME_DESCRIPTION)
                .imageRef(VOLUME_IMAGEREF)
                .build()

        then: "check the volume "
        volume.getName() == VOLUME_NAME
        volume.getDescription() == VOLUME_DESCRIPTION
        volume.getImageRef() == VOLUME_IMAGEREF

        when: "building a Volume the other way"
        Volume anotherVolume = Builders.storage().volume()
                .name(VOLUME_NAME)
                .description(VOLUME_DESCRIPTION)
                .imageRef(VOLUME_IMAGEREF)
                .build()

        then: "check the volume"
        anotherVolume.getName() == VOLUME_NAME
        anotherVolume.getDescription() == VOLUME_DESCRIPTION
        anotherVolume.getImageRef() == VOLUME_IMAGEREF

    }

    def "test heat builders"() {

        when: "when building a Stack"
        HeatStackCreate stack = Builders.stack()
                .name(STACK_NAME)
                .template(STACK_TEMPLATE)
                .parameters(STACK_PARAMS)
                .build()

        then: "check the stack"
        stack.getName() == STACK_NAME
        stack.getTemplate() == STACK_TEMPLATE
        stack.getParameters() == STACK_PARAMS

        when: "building a Stack the other way"
        HeatStackCreate anotherStack = Builders.heat().stack()
                .name(STACK_NAME)
                .template(STACK_TEMPLATE)
                .parameters(STACK_PARAMS)
                .build()

        then: "check the stack"
        anotherStack.getName() == STACK_NAME
        anotherStack.getTemplate() == STACK_TEMPLATE
        anotherStack.getParameters() == STACK_PARAMS
    }

    def "test manila builders"() {

        when: "creating a manila share"
        ShareCreate shareCreate = Builders.share()
                .description(SHARE_DESCRIPTION)
                .shareProto(SHARE_PROTOCOL)
                .shareNetworkId(SHARE_NETWORK_ID)
                .name(SHARE_NAME)
                .isPublic(SHARE_ISPUBLIC)
                .size(SHARE_SIZE)
                .build();

        then: "check the share"
        shareCreate.getDescription() == SHARE_DESCRIPTION
        shareCreate.getShareProto() == SHARE_PROTOCOL
        shareCreate.getShareNetworkId() == SHARE_NETWORK_ID
        shareCreate.getName() == SHARE_NAME
        shareCreate.isPublic() == SHARE_ISPUBLIC
        shareCreate.getSize() == SHARE_SIZE

        when: "building a manila share the other way"
        ShareCreate anotherShareCreate = Builders.manila().share()
                .description(SHARE_DESCRIPTION)
                .shareProto(SHARE_PROTOCOL)
                .shareNetworkId(SHARE_NETWORK_ID)
                .name(SHARE_NAME)
                .isPublic(SHARE_ISPUBLIC)
                .size(SHARE_SIZE)
                .build();

        then: "check the share"
        anotherShareCreate.getDescription() == SHARE_DESCRIPTION
        anotherShareCreate.getShareProto() == SHARE_PROTOCOL
        anotherShareCreate.getShareNetworkId() == SHARE_NETWORK_ID
        anotherShareCreate.getName() == SHARE_NAME
        anotherShareCreate.isPublic() == SHARE_ISPUBLIC
        anotherShareCreate.getSize() == SHARE_SIZE
    }

    def "test sahara builders"() {

        when: "creating a cluster"
        Cluster cluster = Builders.cluster()
                .name(CLUSTER_NAME)
                .pluginName(CLUSTER_PLUGIN_NAME)
                .hadoopVersion(CLUSTER_HADOOP_VERSION)
                .template(CLUSTER_TEMPLATE_ID)
                .image(CLUSTER_IMAGE_ID)
                .keypairName(CLUSTER_KEYPAIR_NAME)
                .managementNetworkId(CLUSTER_MANAGEMENT_NETWORK_ID)
                .build()

        then: "check the cluster"
        cluster.getName() == CLUSTER_NAME
        cluster.getPluginName() == CLUSTER_PLUGIN_NAME
        cluster.getHadoopVersion() == CLUSTER_HADOOP_VERSION
        cluster.getClusterTemplateId() == CLUSTER_TEMPLATE_ID
        cluster.getDefaultImageId() == CLUSTER_IMAGE_ID
        cluster.getUserKeypairId() == CLUSTER_KEYPAIR_NAME
        cluster.getManagementNetworkId() == CLUSTER_MANAGEMENT_NETWORK_ID

        when: "creating a cluster the other way"
        Cluster anotherCluster = Builders.sahara().cluster()
                .name(CLUSTER_NAME)
                .pluginName(CLUSTER_PLUGIN_NAME)
                .hadoopVersion(CLUSTER_HADOOP_VERSION)
                .template(CLUSTER_TEMPLATE_ID)
                .image(CLUSTER_IMAGE_ID)
                .keypairName(CLUSTER_KEYPAIR_NAME)
                .managementNetworkId(CLUSTER_MANAGEMENT_NETWORK_ID)
                .build()

        then: "check the cluster"
        anotherCluster.getName() == CLUSTER_NAME
        anotherCluster.getPluginName() == CLUSTER_PLUGIN_NAME
        anotherCluster.getHadoopVersion() == CLUSTER_HADOOP_VERSION
        anotherCluster.getClusterTemplateId() == CLUSTER_TEMPLATE_ID
        anotherCluster.getDefaultImageId() == CLUSTER_IMAGE_ID
        anotherCluster.getUserKeypairId() == CLUSTER_KEYPAIR_NAME
        anotherCluster.getManagementNetworkId() == CLUSTER_MANAGEMENT_NETWORK_ID

    }



}
