package org.openstack4j.openstack.sahara.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openstack4j.model.compute.Image;
import org.openstack4j.model.sahara.ClusterCreate;
import org.openstack4j.model.sahara.builder.ClusterCreateBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.node.BinaryNode;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * For mapping from object builder to JSON request
 * 
 * @author Ekasit Kijsipongse 
 */


//@JsonRootName("server")
public class SaharaClusterCreate implements ClusterCreate {

    private static final long serialVersionUID = 1L;

    private String name;
    @JsonProperty("hadoop_version")
    private String hadoopVersion;
    @JsonProperty("plugin_name")
    private String pluginName;
    @JsonProperty("cluster_template_id")
    private String clusterTemplateId;
    @JsonProperty("default_image_id")
    private String defaultImageId;
    @JsonProperty("user_keypair_id")
    private String keyName;

    public static ClusterCreateBuilder builder() {
        return new ClusterCreateConcreteBuilder();
    }

    @Override
    public ClusterCreateBuilder toBuilder() {
        return new ClusterCreateConcreteBuilder(this);
    }

    @Override
    public String getName() {
        return name;
    }

    public static class ClusterCreateConcreteBuilder implements ClusterCreateBuilder {

        SaharaClusterCreate m;

        ClusterCreateConcreteBuilder() {
            this(new SaharaClusterCreate());
        }

        ClusterCreateConcreteBuilder(SaharaClusterCreate m) {
            this.m = m;
        }

        @Override
        public ClusterCreateConcreteBuilder name(String name) {
            m.name = name;
            return this;
        }

        @Override
        public ClusterCreateConcreteBuilder hadoopVersion(String hadoopVersion) {
            m.hadoopVersion = hadoopVersion;
            return this;
        }

        @Override
        public ClusterCreateConcreteBuilder pluginName(String pluginName) {
            m.pluginName = pluginName;
            return this;
        }

        @Override
        public ClusterCreateConcreteBuilder template(String clusterTemplateId) {
            m.clusterTemplateId = clusterTemplateId;
            return this;
        }

        @Override
        public ClusterCreateConcreteBuilder image(String imageId) {
            m.defaultImageId = imageId;
            return this;
        }

        public ClusterCreateConcreteBuilder image(Image image) {
            m.defaultImageId = image.getId();
            return this;
        }

        @Override
        public ClusterCreateConcreteBuilder keypairName(String keypairName) {
            m.keyName = keypairName;
            return this;
        }

        @Override
        public ClusterCreate build() {
            return m;
        }

        @Override
        public ClusterCreateConcreteBuilder from(ClusterCreate in) {
            m = (SaharaClusterCreate)in;
            return this;
        }

    }
}
