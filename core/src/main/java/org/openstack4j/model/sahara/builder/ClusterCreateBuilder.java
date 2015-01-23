package org.openstack4j.model.sahara.builder;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.sahara.ClusterCreate;

/**
 * Builder interface used for {@link ClusterCreate} object.
 * 
 * @author Ekasit Kijsipongse
 */
public interface ClusterCreateBuilder extends Builder<ClusterCreateBuilder, ClusterCreate> {

        //
        // Define setter apis for user to create a new cluster
        //
        
	/**
	 * See {@link ClusterCreate#getName()}
	 * 
	 * @param name the name of the cluster
	 * @return ClusterCreateBuilder
	 */
	ClusterCreateBuilder name(String name);
	
	/**
	 * See {@link ClusterCreate#getHadoopVersion()}
	 * 
	 * @param hadoopVersion the version of hadoop
	 * @return ClusterCreateBuilder
	 */
	ClusterCreateBuilder hadoopVersion(String hadoopVersion);

	/**
	 * See {@link ClusterCreate#getPluginName()}
	 * 
	 * @param pluginName the name of the sahara plugin
	 * @return ClusterCreateBuilder
	 */
	ClusterCreateBuilder pluginName(String pluginName);

	/**
	 * See {@link ClusterCreate#UNDERCONSTRUCT()}
	 * 
	 * @param clusterTemplateId the id of cluster template
	 * @return ClusterCreateBuilder
	 */
	ClusterCreateBuilder template(String clusterTemplateId);

	/**
	 * See {@link ClusterCreate#UNDERCONSTRUCT()}
	 * 
	 * @param imageId the id of image
	 * @return ClusterCreateBuilder
	 */
	ClusterCreateBuilder image(String imageId);

	/**
	 * See {@link ClusterCreate#UNDERCONSTRUCT()}
	 * 
	 * @param keypairName the name of key pair
	 * @return ClusterCreateBuilder
	 */
	ClusterCreateBuilder keypairName(String keypairName);
}
