package org.openstack4j.model.sahara;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.sahara.builder.ClusterCreateBuilder;

/**
 * The interface describes the model of a {@link Cluster} used to create a new cluster
 * 
 * @author Ekasit Kijsipongse
 */
public interface ClusterCreate extends ModelEntity, Buildable<ClusterCreateBuilder> {

        // 
        // Define getter apis for WHAT???
        //  

	/**
	 * The descriptive name for the cluster
	 *
	 * @return the name of the cluster
	 */
	String getName();

	/**
	 * The version of hadoop
	 *
	 * @return the version of hadoop
	 */
	//String getHadoopVersion();

	/**
	 * The name of sahara plugin
	 *
	 * @return the name of sahara plugin
	 */
	//String getPluginName();
	
	/**
	 * The id of image
	 *
	 * @return the id of image
	 */
	//String getImageId();
	
	/**
	 * The name of key pair
	 *
	 * @return the name of key pair
	 */
	//String getKeyName();
}
