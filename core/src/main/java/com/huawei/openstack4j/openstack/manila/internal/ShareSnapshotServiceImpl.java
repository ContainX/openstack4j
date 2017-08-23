/*******************************************************************************
 * 	Copyright 2016 ContainX and OpenStack4j                                          
 * 	                                                                                 
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
package com.huawei.openstack4j.openstack.manila.internal;

import java.util.List;

import com.huawei.openstack4j.api.manila.ShareSnapshotService;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.manila.ShareSnapshot;
import com.huawei.openstack4j.model.manila.ShareSnapshotCreate;
import com.huawei.openstack4j.model.manila.ShareSnapshotUpdateOptions;
import com.huawei.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import com.huawei.openstack4j.openstack.manila.domain.ManilaShareSnapshot;
import com.huawei.openstack4j.openstack.manila.domain.ManilaShareSnapshotUpdate;
import com.huawei.openstack4j.openstack.manila.domain.actions.ShareSnapshotAction;
import com.huawei.openstack4j.openstack.manila.domain.actions.ShareSnapshotActions;

import static com.google.common.base.Preconditions.checkNotNull;

public class ShareSnapshotServiceImpl extends BaseShareServices implements ShareSnapshotService {
    @Override
    public ShareSnapshot create(ShareSnapshotCreate snapshotCreate) {
        checkNotNull(snapshotCreate);
        return post(ManilaShareSnapshot.class, uri("/snapshots"))
                .entity(snapshotCreate)
                .execute();
    }

    @Override
    public List<? extends ShareSnapshot> list() {
        return list(false);
    }

    @Override
    public List<? extends ShareSnapshot> listDetails() {
        return list(true);
    }

    private List<? extends ShareSnapshot> list(boolean detail) {
        return get(ManilaShareSnapshot.ShareSnapshots.class, uri("/snapshots" +  (detail ? "/detail" : "")))
                .execute()
                .getList();
    }

    @Override
    public ShareSnapshot get(String snapshotId) {
        checkNotNull(snapshotId);
        return get(ManilaShareSnapshot.class, uri("/snapshots/%s", snapshotId)).execute();
    }

    @Override
    public ShareSnapshot update(String snapshotId, ShareSnapshotUpdateOptions snapshotUpdateOptions) {
        checkNotNull(snapshotId);
        checkNotNull(snapshotUpdateOptions);

        return put(ManilaShareSnapshot.class, uri("/snapshots/%s", snapshotId))
                .entity(ManilaShareSnapshotUpdate.fromOptions(snapshotUpdateOptions))
                .execute();
    }

    @Override
    public ActionResponse delete(String snapshotId) {
        checkNotNull(snapshotId);
        return ToActionResponseFunction.INSTANCE.apply(
                delete(Void.class, uri("/snapshots/%s", snapshotId)).executeWithResponse());
    }

    @Override
    public ActionResponse resetState(String snapshotId, ShareSnapshot.Status status) {
        checkNotNull(snapshotId);
        checkNotNull(status);

        return invokeAction(snapshotId, ShareSnapshotActions.resetState(status));
    }

    @Override
    public ActionResponse forceDelete(String snapshotId) {
        checkNotNull(snapshotId);
        return invokeAction(snapshotId, ShareSnapshotActions.forceDelete());
    }

    /**
     * Invoke the action on the given snapshot.
     *
     * @param snapshotId the snapshot ID
     * @param action the action to invoke
     * @return the action response of the server
     */
    private ActionResponse invokeAction(String snapshotId, ShareSnapshotAction action) {
        return ToActionResponseFunction.INSTANCE.apply(
                post(Void.class, uri("/snapshots/%s/action", snapshotId))
                        .entity(action)
                        .executeWithResponse());
    }
}
