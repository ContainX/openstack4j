package org.openstack4j.openstack.sahara.domain;

import java.util.Date;
import java.util.List;

import org.openstack4j.model.sahara.Cluster;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;

/**
 * For mapping JSON response to java objects
 * 
 * @author Ekasit Kijsipongse 
 */

@JsonRootName("cluster")
@JsonIgnoreProperties(ignoreUnknown=true)
public class SaharaCluster implements Cluster {

	public static final long serialVersionUID = 1L;
	public String id;
	public String name;
        public String description;
	public Status status;
	@JsonProperty("plugin_name")
        public String plugin;
	@JsonProperty("hadoop_version")
        public String hadoopVersion;
	@JsonProperty("tenant_id")
	public String tenantId;
	@JsonProperty("created_at")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date createdAt;
	@JsonProperty("default_image_id")
	public String baseImage;

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public Status getStatus() {
		return status;
	}

	@Override
	public String getPlugin() {
		return plugin;
	}

	@Override
	public String getBaseImage() {
                return baseImage;
	}
	
	@Override
	public Date getCreatedAt() {
		return createdAt;
	}

	@Override
	public String getHadoopVersion() {
		return hadoopVersion;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).omitNullValues()
				   .add("id",id)
                                   .add("name", name)
                                   .add("description", description)
				   .add("status", status)
                                   .add("created", createdAt)
                                   .add("tenantId", tenantId)
				   .toString();
	}
	
	public static class Clusters extends ListResult<SaharaCluster> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("clusters")
		private List<SaharaCluster> clusters;
		
		public List<SaharaCluster> value() {
			return clusters;
		}
	}
}
