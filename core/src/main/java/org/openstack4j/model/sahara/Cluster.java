package org.openstack4j.model.sahara;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.sahara.builder.ClusterBuilder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.CaseFormat;

/**
 * An OpenStack Cluster
 * 
 * @author ekasit.kijsipongse@nectec.or.th
 */
public interface Cluster extends ModelEntity, Buildable<ClusterBuilder> {

	/**
	 * The current cluster Status
	 * 
	 */
	public enum Status {
		ACTIVE, CREATING, UNRECOGNIZED;
		
		@JsonValue
		public String value() {
			return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, name());
		}

		@Override
		public String toString() {
			return value();
		}

		@JsonCreator
		public static Status fromValue(String status) {
			try {
				return valueOf(CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_UNDERSCORE, checkNotNull(status, "status must not be null")));
			} catch (IllegalArgumentException e) {
				return UNRECOGNIZED;
			}
		}
	}
	
	/**
	 * @return the status of the cluster
	 */
	Status getStatus();
	
	/**
	 * @return the information of the cluster
	 */
	Map<String, ? extends ServiceInfo> getInfos();

	/**
	 * @return the template id of the cluster
	 */
	String getClusterTemplateId();

	/**
	 * @return the if the cluster is transient 
	 */
	Boolean isTransient();

	/**
	 * @return the description of the cluster
	 */
	String getDescription();
	
	/**
	 * @return the configurations of the cluster
	 */
	Map<String,? extends ServiceConfig> getClusterConfigs();

	/**
	 * @return the created date of the cluster
	 */
	Date getCreatedAt();

	/**
	 * @return the default image id of the cluster
	 */
	String getDefaultImageId();
	
	/**
	 * @return the user keypair id of the cluster
	 */
	String getUserKeypairId();
	
	/**
	 * @return the updated date of the cluster
	 */
	Date getUpdatedAt();

	/**
	 * @return the plugin name of the cluster
	 */
	String getPluginName();
	
	/**
	 * @return the management network of the cluster
	 */
	String getManagementNetworkId();
	
	/**
	 * @return the anti-affinity of the cluster
	 */
	List<String> getAntiAffinity();
	
	/**
	 * @return the tenant id of the cluster
	 */
	String getTenantId();
	 
	/**
	 * @return the node groups of the cluster
	 */
	List<? extends NodeGroup> getNodeGroups();
	 
	/**
	 * @return the management public key of the cluster
	 */
	String getManagementPublicKey();
	 
	/**
	 * @return the status description of the cluster
	 */
	String getStatusDescription();
	 
	/**
	 * @return the hadoop version of the cluster
	 */
	String getHadoopVersion();
	
	/**
	 * @return the identifier of the cluster
	 */
	String getId();
	
	/**
	 * @return the trust id of the cluster
	 */
	String getTrustId();
	
	/**
	 * @return the name of the cluster
	 */
	String getName();
	
}
