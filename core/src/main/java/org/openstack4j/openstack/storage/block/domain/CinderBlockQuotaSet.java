package org.openstack4j.openstack.storage.block.domain;

import static com.google.common.base.MoreObjects.toStringHelper;

import org.openstack4j.model.storage.block.BlockQuotaSet;
import org.openstack4j.model.storage.block.builder.BlockQuotaSetBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * An OpenStack Quota-Set
 *
 * @author Jeremy Unruh
 */
@JsonRootName("quota_set")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CinderBlockQuotaSet implements BlockQuotaSet {

    private static final long serialVersionUID = 1L;

    @JsonProperty
    private String id;
    @JsonProperty
    private int snapshots;
    @JsonProperty
    private int volumes;
    @JsonProperty
    private int gigabytes;

    public static BlockQuotaSetBuilder builder() {
        return new BlockQuotaSetConcreteBuilder();
    }

    @Override
    public BlockQuotaSetBuilder toBuilder() {
        return new BlockQuotaSetConcreteBuilder(this);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public int getSnapshots() {
        return snapshots;
    }

    @Override
    public int getVolumes() {
        return volumes;
    }

    @Override
    public int getGigabytes() {
        return gigabytes;
    }

    @Override
    public String toString() {
        return toStringHelper(this).add("snapshots", snapshots).add("volumes", volumes).add("gigabytes", gigabytes).toString();
    }

    public static class BlockQuotaSetConcreteBuilder implements BlockQuotaSetBuilder {

        private CinderBlockQuotaSet model;

        BlockQuotaSetConcreteBuilder() {
            this.model = new CinderBlockQuotaSet();
        }

        BlockQuotaSetConcreteBuilder(CinderBlockQuotaSet model) {
            this.model = model;
            this.model.id = null;
        }

        @Override
        public BlockQuotaSet build() {
            return model;
        }

        @Override
        public BlockQuotaSetBuilder from(BlockQuotaSet in) {
            return new BlockQuotaSetConcreteBuilder((CinderBlockQuotaSet) in);
        }

        @Override
        public BlockQuotaSetBuilder volumes(int volumes) {
            model.volumes = volumes;
            return this;
        }

        @Override
        public BlockQuotaSetBuilder snapshots(int snapshots) {
            model.snapshots = snapshots;
            return this;
        }

        @Override
        public BlockQuotaSetBuilder gigabytes(int gigabytes) {
            model.gigabytes = gigabytes;
            return this;
        }

    }

}
