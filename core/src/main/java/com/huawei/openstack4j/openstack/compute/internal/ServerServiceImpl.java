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
package com.huawei.openstack4j.openstack.compute.internal;

import static com.google.common.base.Preconditions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huawei.openstack4j.api.Apis;
import com.huawei.openstack4j.api.compute.ServerService;
import com.huawei.openstack4j.api.compute.ext.InstanceActionsService;
import com.huawei.openstack4j.api.compute.ext.InterfaceService;
import com.huawei.openstack4j.core.transport.ExecutionOptions;
import com.huawei.openstack4j.core.transport.HttpResponse;
import com.huawei.openstack4j.core.transport.propagation.PropagateOnStatus;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.compute.Action;
import com.huawei.openstack4j.model.compute.RebootType;
import com.huawei.openstack4j.model.compute.Server;
import com.huawei.openstack4j.model.compute.Server.Status;
import com.huawei.openstack4j.model.compute.ServerCreate;
import com.huawei.openstack4j.model.compute.ServerPassword;
import com.huawei.openstack4j.model.compute.ServerUpdateOptions;
import com.huawei.openstack4j.model.compute.VNCConsole;
import com.huawei.openstack4j.model.compute.VNCConsole.Type;
import com.huawei.openstack4j.model.compute.VolumeAttachment;
import com.huawei.openstack4j.model.compute.actions.BackupOptions;
import com.huawei.openstack4j.model.compute.actions.EvacuateOptions;
import com.huawei.openstack4j.model.compute.actions.LiveMigrateOptions;
import com.huawei.openstack4j.model.compute.actions.RebuildOptions;
import com.huawei.openstack4j.model.compute.builder.ServerCreateBuilder;
import com.huawei.openstack4j.openstack.common.Metadata;
import com.huawei.openstack4j.openstack.compute.domain.AdminPass;
import com.huawei.openstack4j.openstack.compute.domain.ConsoleOutput;
import com.huawei.openstack4j.openstack.compute.domain.ConsoleOutputOptions;
import com.huawei.openstack4j.openstack.compute.domain.NovaPassword;
import com.huawei.openstack4j.openstack.compute.domain.NovaServer;
import com.huawei.openstack4j.openstack.compute.domain.NovaServer.Servers;
import com.huawei.openstack4j.openstack.compute.domain.NovaServerCreate;
import com.huawei.openstack4j.openstack.compute.domain.NovaServerCreateReturnReservationIdWrap;
import com.huawei.openstack4j.openstack.compute.domain.NovaServerUpdate;
import com.huawei.openstack4j.openstack.compute.domain.NovaVNCConsole;
import com.huawei.openstack4j.openstack.compute.domain.NovaVolumeAttachment;
import com.huawei.openstack4j.openstack.compute.domain.actions.BackupAction;
import com.huawei.openstack4j.openstack.compute.domain.actions.BasicActions;
import com.huawei.openstack4j.openstack.compute.domain.actions.BasicActions.ChangePassword;
import com.huawei.openstack4j.openstack.compute.domain.actions.BasicActions.ConfirmResize;
import com.huawei.openstack4j.openstack.compute.domain.actions.BasicActions.Migrate;
import com.huawei.openstack4j.openstack.compute.domain.actions.BasicActions.Reboot;
import com.huawei.openstack4j.openstack.compute.domain.actions.BasicActions.Resize;
import com.huawei.openstack4j.openstack.compute.domain.actions.BasicActions.RevertResize;
import com.huawei.openstack4j.openstack.compute.domain.actions.CreateSnapshotAction;
import com.huawei.openstack4j.openstack.compute.domain.actions.EvacuateAction;
import com.huawei.openstack4j.openstack.compute.domain.actions.LiveMigrationAction;
import com.huawei.openstack4j.openstack.compute.domain.actions.RebuildAction;
import com.huawei.openstack4j.openstack.compute.domain.actions.ResetStateAction;
import com.huawei.openstack4j.openstack.compute.domain.actions.SecurityGroupActions;
import com.huawei.openstack4j.openstack.compute.domain.actions.ServerAction;
import com.huawei.openstack4j.openstack.compute.functions.ToActionResponseFunction;
import com.huawei.openstack4j.openstack.compute.functions.WrapServerIfApplicableFunction;

/**
 * Server Operation API implementation
 *
 * @author Jeremy Unruh
 */
public class ServerServiceImpl extends BaseComputeServices implements ServerService {

    private static final Logger LOG = LoggerFactory.getLogger(ServerServiceImpl.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Server> list() {
        return list(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Server> list(boolean detail) {
        return list(detail, Boolean.FALSE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Server> listAll(boolean detail) {
        return list(detail, Boolean.TRUE);
    }

    private List<? extends Server> list(boolean detail, boolean allTenants) {
        Invocation<Servers> req = get(Servers.class, uri("/servers" + ((detail) ? "/detail" : "")));
        if (allTenants)
            req.param("all_tenants", 1);
        return req.execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Server> list(Map<String, String> filteringParams) {
        Invocation<Servers> serverInvocation = get(Servers.class, "/servers/detail");
        if (filteringParams != null) {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                serverInvocation = serverInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return serverInvocation.execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Server get(String serverId) {
        checkNotNull(serverId);
        return get(NovaServer.class, uri("/servers/%s", serverId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
	public Server boot(ServerCreate server) {
		checkNotNull(server);
		// update return reservation-id to true
		NovaServerCreateReturnReservationIdWrap wrapped = new NovaServerCreateReturnReservationIdWrap(server, false);
		return post(NovaServer.class, uri("/servers"))
				.entity(WrapServerIfApplicableFunction.INSTANCE.apply(wrapped))
				.execute();
	}
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String bootAndReturnReservationId(ServerCreate server) {
        checkNotNull(server);
        // update return reservation-id to true
        NovaServerCreateReturnReservationIdWrap wrapped = new NovaServerCreateReturnReservationIdWrap(server, true);
        return post(HashMap.class, uri("/servers"))
                     .entity(WrapServerIfApplicableFunction.INSTANCE.apply(wrapped))
                     .execute()
                     .get("reservation_id").toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Server bootAndWaitActive(ServerCreate server, int maxWaitTime) {
        return waitForServerStatus(boot(server).getId(), Status.ACTIVE, maxWaitTime, TimeUnit.MILLISECONDS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String serverId) {
        checkNotNull(serverId);
        return ToActionResponseFunction.INSTANCE.apply(
                    delete(Void.class, uri("/servers/%s", serverId)).executeWithResponse()
               );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse action(String serverId, Action action) {
        checkNotNull(serverId);

        ServerAction instance = BasicActions.actionInstanceFor(action);
        if (instance == null)
            return ActionResponse.actionFailed(String.format("Action %s was not found in the list of invokable actions", action), 412);

        return invokeAction(serverId, instance);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String createSnapshot(String serverId, String snapshotName) {
        checkNotNull(serverId);
        checkNotNull(snapshotName);

        HttpResponse response = invokeActionWithResponse(serverId, CreateSnapshotAction.create(snapshotName));
        String id = null;
        if (response.getStatus() == 202) {
            String location = response.header("location");
            if (location != null && location.contains("/"))
            {
                String[] s = location.split("/");
                id = s[s.length - 1];
            }

        }
        response.getEntity(Void.class);
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse reboot(String serverId, RebootType type) {
        checkNotNull(serverId);
        return invokeAction(serverId, new Reboot(type));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse rebuild(String serverId, RebuildOptions options) {
        checkNotNull(serverId);
        return invokeAction(serverId, RebuildAction.create(options));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse resize(String serverId, String flavorId) {
        checkNotNull(serverId);
        checkNotNull(flavorId);

        return invokeAction(serverId, new Resize(flavorId));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse addSecurityGroup(String serverId, String secGroupName) {
        checkNotNull(serverId);
        checkNotNull(secGroupName);
        return invokeAction(serverId, SecurityGroupActions.add(secGroupName));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse removeSecurityGroup(String serverId, String secGroupName) {
        checkNotNull(serverId);
        checkNotNull(secGroupName);
        return invokeAction(serverId, SecurityGroupActions.remove(secGroupName));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse confirmResize(String serverId) {
        checkNotNull(serverId);
        return invokeAction(serverId, BasicActions.instanceFor(ConfirmResize.class));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse revertResize(String serverId) {
        checkNotNull(serverId);
        return invokeAction(serverId, BasicActions.instanceFor(RevertResize.class));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getConsoleOutput(String serverId, int numLines) {
        checkNotNull(serverId);

        // Build options with the given numLines or default to full output
        ConsoleOutputOptions consoleOutputOptions;
        if (numLines <= 0)
        	consoleOutputOptions = new ConsoleOutputOptions();
        else
        	consoleOutputOptions = new ConsoleOutputOptions(numLines);

        ConsoleOutput c = post(ConsoleOutput.class, uri("/servers/%s/action", serverId))
                .entity(consoleOutputOptions).execute();
        return (c != null) ? c.getOutput() : null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VNCConsole getVNCConsole(String serverId, Type type) {
        checkNotNull(serverId);
        if (type == null)
            type = Type.NOVNC;

        return post(NovaVNCConsole.class, uri("/servers/%s/action", serverId))
                    .entity(NovaVNCConsole.getConsoleForType(type))
                    .execute();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, ? extends Number> diagnostics(String serverId) {
        return get(HashMap.class, uri("/servers/%s/diagnostics", serverId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ServerCreateBuilder serverBuilder() {
        return NovaServerCreate.builder();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VolumeAttachment attachVolume(String serverId, String volumeId, String device) {
        return post(NovaVolumeAttachment.class, uri("/servers/%s/os-volume_attachments", serverId))
                .entity(NovaVolumeAttachment.create(volumeId, device))
                .execute(ExecutionOptions.<NovaVolumeAttachment>create(PropagateOnStatus.on(404)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse detachVolume(String serverId, String attachmentId) {
        return ToActionResponseFunction.INSTANCE.apply(
                   delete(Void.class,uri("/servers/%s/os-volume_attachments/%s", serverId, attachmentId)).executeWithResponse()
                );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse migrateServer(String serverId) {
        checkNotNull(serverId);
        return invokeAction(serverId, BasicActions.instanceFor(Migrate.class));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse liveMigrate(String serverId, LiveMigrateOptions options) {
        checkNotNull(serverId);
        if (options == null)
            options = LiveMigrateOptions.create();
        return invokeAction(serverId, LiveMigrationAction.create(options));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse resetState(String serverId, Status state) {
        checkNotNull(serverId);
        checkNotNull(state);
        return invokeAction(serverId, ResetStateAction.create(state));
    }

    /**
     * {{@link #invokeAction(String, String)}
     */
    @Override
    public ActionResponse backupServer(String serverId, BackupOptions options) {
        checkNotNull(serverId);
        checkNotNull(options);
        return invokeAction(serverId, BackupAction.create(options));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse changeAdminPassword(String serverId, String adminPassword) {
        checkNotNull(serverId);
        checkNotNull(adminPassword);
        return invokeAction(serverId, new ChangePassword(adminPassword));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Server waitForServerStatus(String serverId, Status status, int maxWait, TimeUnit maxWaitUnit) {
        checkNotNull(serverId);
        Server server = null;
        long duration = 0;
        long maxTime = maxWaitUnit.toMillis(maxWait);
        while ( duration < maxTime ) {
            server = get(serverId);

            if (server == null || server.getStatus() == status || server.getStatus() == Status.ERROR)
                break;

            duration += sleep(1000);
        }
        return server;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> getMetadata(String serverId) {
        checkNotNull(serverId);
        return get(Metadata.class, uri("/servers/%s/metadata", serverId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> updateMetadata(String serverId, Map<String, String> metadata) {
        checkNotNull(serverId);
        checkNotNull(metadata);
        return put(Metadata.class, uri("/servers/%s/metadata", serverId)).entity(Metadata.toMetadata(metadata)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse deleteMetadataItem(String serverId, String key) {
        checkNotNull(serverId);
        checkNotNull(key);
        return ToActionResponseFunction.INSTANCE.apply(
                  delete(Void.class, uri("/servers/%s/metadata/%s", serverId, key)).executeWithResponse()
                );
    }

    private int sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            LOG.error(e.getMessage(), e);
        }
        return ms;
    }

    @Override
    public Server update(String serverId, ServerUpdateOptions options) {
        checkNotNull(serverId);
        checkNotNull(options);

        return put(NovaServer.class, uri("/servers/%s", serverId)).entity(NovaServerUpdate.fromOptions(options)).execute();
    }

    @Override
    public InterfaceService interfaces() {
        return Apis.get(InterfaceService.class);
    }

    @Override
    public InstanceActionsService instanceActions() {
        return Apis.get(InstanceActionsService.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ServerPassword getPassword(String serverId) {
        checkNotNull(serverId);
        return get(NovaPassword.class, uri("/servers/%s/os-server-password", serverId)).execute();
    }   
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ServerPassword evacuate(String serverId, EvacuateOptions options) {
        checkNotNull(serverId);
      
        return post(AdminPass.class, uri("/servers/%s/action", serverId))
                    .entity(EvacuateAction.create(options))
                    .execute();            
    }
}
