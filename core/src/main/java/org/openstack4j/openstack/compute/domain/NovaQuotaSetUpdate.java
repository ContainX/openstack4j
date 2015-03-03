package org.openstack4j.openstack.compute.domain;

import org.openstack4j.model.compute.QuotaSetUpdate;
import org.openstack4j.model.compute.builder.QuotaSetUpdateBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("unused")
public class NovaQuotaSetUpdate implements QuotaSetUpdate {

    private static final long serialVersionUID = 1L;

    @JsonProperty("metadata_items")
    private Integer metadataItems;

    @JsonProperty("injected_file_content_bytes")
    private Integer injectedFileContentBytes;

    @JsonProperty("injected_files")
    private Integer injectedFiles;

    private Integer gigabytes;
    private Integer ram;

    @JsonProperty("floating_ips")
    private Integer floatingIps;

    private Integer instances;

    private Integer volumes;

    private Integer cores;

    @JsonProperty("security_groups")
    private Integer securityGroups;

    @JsonProperty("security_group_rules")
    private Integer securityGroupRules;

    @JsonProperty("injected_file_path_bytes")
    private Integer injectedFilePathBytes;

    @JsonProperty("key_pairs")
    private Integer keyPairs;

    @Override
    public QuotaSetUpdateBuilder toBuilder() {
        return new QuotaSetUpdateConcreteBuilder(this);
    }
    
    public static QuotaSetUpdateBuilder builder() {
        return new QuotaSetUpdateConcreteBuilder();
    }
    
    public static class QuotaSetUpdateConcreteBuilder implements QuotaSetUpdateBuilder {

        private NovaQuotaSetUpdate model;
        
        public QuotaSetUpdateConcreteBuilder() {
            this.model = new NovaQuotaSetUpdate();
        }
        
        public QuotaSetUpdateConcreteBuilder(QuotaSetUpdate model) {
            this.model = (NovaQuotaSetUpdate) model;
        }
        
        @Override
        public QuotaSetUpdate build() {
            return model;
        }

        @Override
        public QuotaSetUpdateBuilder from(QuotaSetUpdate in) {
            return new QuotaSetUpdateConcreteBuilder(in);
        }

        @Override
        public QuotaSetUpdateBuilder metadataItems(int metadataitems) {
            model.metadataItems = metadataitems;
            return this;
        }

        @Override
        public QuotaSetUpdateBuilder injectedFileContentBytes(int injectedFileContentBytes) {
            model.injectedFileContentBytes = injectedFileContentBytes;
            return this;
        }

        @Override
        public QuotaSetUpdateBuilder injectedFiles(int injectedFiles) {
            model.injectedFiles = injectedFiles;
            return this;
        }

        @Override
        public QuotaSetUpdateBuilder gigabytes(int gigabytes) {
            model.gigabytes = gigabytes;
            return this;
        }

        @Override
        public QuotaSetUpdateBuilder ram(int ram) {
            model.ram = ram;
            return this;
        }

        @Override
        public QuotaSetUpdateBuilder floatingIps(int floatingIps) {
            model.floatingIps = floatingIps;
            return this;
        }

        @Override
        public QuotaSetUpdateBuilder instances(int instances) {
            model.instances = instances;
            return this;
        }

        @Override
        public QuotaSetUpdateBuilder volumes(int volumes) {
            model.volumes = volumes;
            return this;
        }

        @Override
        public QuotaSetUpdateBuilder cores(int cores) {
            model.cores = cores;
            return this;
        }

        @Override
        public QuotaSetUpdateBuilder securityGroups(int securityGroups) {
            model.securityGroups = securityGroups;
            return this;
        }

        @Override
        public QuotaSetUpdateBuilder securityGroupRules(int securityGroupRules) {
            model.securityGroupRules = securityGroupRules;
            return this;
        }

        @Override
        public QuotaSetUpdateBuilder injectedFilePathBytes(int injectedFilePathBytes) {
            model.injectedFilePathBytes = injectedFilePathBytes;
            return this;
        }

        @Override
        public QuotaSetUpdateBuilder keyPairs(int keyPairs) {
            model.keyPairs = keyPairs;
            return this;
        }
    }
    
}
