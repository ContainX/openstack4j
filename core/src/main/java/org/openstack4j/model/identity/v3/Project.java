package org.openstack4j.model.identity.v3;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.identity.builder.ProjectBuilder;
import org.openstack4j.model.identity.builder.TenantBuilder;

/**
 * Project Model class use to group/isolate resources and/or identity objects
 * Project is the new name for tenants in Keystone v3.
 * 
 * @author Sujai SD
 */
public interface Project extends ModelEntity , Buildable<ProjectBuilder>{
    
    /**
     * @return the unique identifier for this project
     */
    String getId();
        
    /**
    * @return the name of the project
    */
   String getName();

   /**
    * @return the description of the project
    */
   String getDescription();

   /**
    * @return if the project is enabled
    */
   boolean isEnabled();
   
   /**
     * @return the id of the domain
     */
   String getDomainId();
   
   void delete();
       

}
