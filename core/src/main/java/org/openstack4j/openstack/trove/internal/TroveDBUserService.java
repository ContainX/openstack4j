/*******************************************************************************
 * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not      
 * 	use this file except in compliance with the License. You may obtain a copy of    
 * 	the License at                                                                   
 * 	                                                                                 
 * 	    http://www.apache.org/licenses/LICENSE-2.0                                   
 * 	                                                                                 
 * 	Unless required by applicable law or agreed to in writing, software              
 * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT        
 * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the         
 * 	License for the specific language governing permissions and limitations under    
 * 	the License.                                                                     
 *******************************************************************************/
package org.openstack4j.openstack.trove.internal;

import static com.google.common.base.Preconditions.*;

import java.util.List;

import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.openstack.trove.domain.TroveDatabase;
import org.openstack4j.openstack.trove.domain.TroveDatabase.Databases;
import org.openstack4j.openstack.trove.domain.TroveDatabaseUser;
import org.openstack4j.openstack.trove.domain.TroveDatabaseUser.DatabaseUsers;

/**
 * The implementation of manipulation of {@link TroveDatabaseUser}
 *
 * @author QianBiao.NG
 * @date   2017-07-31 11:50:37
 */
public class TroveDBUserService extends BaseTroveServices {
	

    /**
     * Returns list of all users for the database instance
     * @param instanceId
     * @return the list of users for the database instance
     */
    public List<TroveDatabaseUser> list(String instanceId) {
        return get(DatabaseUsers.class, uri("/instances/%s/users",instanceId)).execute().getList();
    }


    /**
     * Returns list of all databases which the user has access to on the database instance
     * @param instanceId
     * @param userName
     * @return the list of databases for a user
     */
    public List<TroveDatabase> listUserDatabases(String instanceId, String userName) {
        return get(Databases.class, uri("/instances/%s/users/%s/databases",instanceId,userName)).execute().getList();
    }

    /**
     * Create a user for the database instance
     * @param instanceId
     * @param databaseUsers
     * @return the action response
     */
    public ActionResponse create(String instanceId, DatabaseUsers databaseUsers) {
        return post(ActionResponse.class, uri("/instances/%s/users",instanceId)).entity(databaseUsers).execute();
    }

    /**
     * Deletes a user for the database instance
     * @param instanceId
     * @param userName
     * @return the action response
     */
    public ActionResponse delete(String instanceId, String userName) {
        checkNotNull(instanceId);
        checkNotNull(userName);
        return deleteWithResponse(uri("/instances/%s/users/%s",instanceId,userName)).execute();
    }

    /**
     * Grant user access to a database on the database instance
     * @param instanceId
     * @param userName
     * @param databases
     * @return the action response
     */
    public ActionResponse grantUserDBAccess(String instanceId, String userName, Databases databases) {
        checkNotNull(instanceId);
        checkNotNull(userName);
        checkNotNull(databases);
        return put(ActionResponse.class, uri("/instances/%s/users/%s/databases",instanceId,userName)).entity(databases).execute();
    }

    /**
     * Revoke user access to a database on the database instance
     * @param instanceId
     * @param userName
     * @param dbName
     * @return the action response
     */
    public ActionResponse revokeUserDBAccess(String instanceId, String userName, String dbName) {
        checkNotNull(instanceId);
        checkNotNull(userName);
        checkNotNull(dbName);
        return deleteWithResponse(uri("/instances/%s/users/%s/databases/%s",instanceId,userName,dbName)).execute();
    }

}
