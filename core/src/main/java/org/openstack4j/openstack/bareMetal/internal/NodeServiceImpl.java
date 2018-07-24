package org.openstack4j.openstack.bareMetal.internal;

import org.openstack4j.api.bareMetal.NodeService;
import org.openstack4j.model.bareMetal.*;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.openstack.bareMetal.domain.BareMetalNode;
import org.openstack4j.openstack.bareMetal.domain.BareMetalNodeSupportedBootDevices;
import org.openstack4j.openstack.bareMetal.domain.BareMetalNodeValidate;

import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Node (Bare Metal Node) Operation API implementation
 *
 * @author zhangliang
 */
public class NodeServiceImpl extends BaseBareMetalServices implements NodeService {

    @Override
    public List<? extends Node> list() {
        return get(BareMetalNode.Nodes.class, uri("/nodes")).execute().getList();
    }

    @Override
    public List<? extends Node> list(Map<String, Object> headers) {
        checkNotNull(headers);
        return get(BareMetalNode.Nodes.class, uri("/nodes")).headers(headers).execute().getList();
    }

    @Override
    public Node create(Node node) {
        checkNotNull(node);
        return post(BareMetalNode.class, uri("/nodes")).entity(node).execute();
    }

    @Override
    public Node findOne(String nodeIdent) {
        checkNotNull(nodeIdent);
        return get(BareMetalNode.class, uri("/nodes/%s", nodeIdent)).execute();
    }

    @Override
    public Node findOne(String nodeIdent, Map<String, Object> headers) {
        checkNotNull(nodeIdent);
        checkNotNull(headers);
        return get(BareMetalNode.class, uri("/nodes/%s", nodeIdent)).headers(headers).execute();
    }

    @Override
    public ActionResponse delete(String nodeIdent) {
        checkNotNull(nodeIdent);
        return deleteWithResponse(uri("/nodes/%s", nodeIdent)).execute();
    }

    @Override
    public Node update(String nodeIdent, NodeUpdate nodeUpdate) {
        checkNotNull(nodeIdent);
        checkNotNull(nodeUpdate);
        return patch(BareMetalNode.class, uri("/nodes/%s", nodeIdent)).entity(nodeUpdate).execute();
    }

    @Override
    public ActionResponse setMaintenance(String nodeIdent) {
        checkNotNull(nodeIdent);
        return put(ActionResponse.class, uri("/nodes/%s/maintenance", nodeIdent)).execute();
    }

    @Override
    public ActionResponse clearMaintenance(String nodeIdent) {
        checkNotNull(nodeIdent);
        return deleteWithResponse(uri("/nodes/%s/maintenance", nodeIdent)).execute();
    }

    @Override
    public ActionResponse changePowerState(String nodeIdent, NodeManagement nodeManagement) {
        checkNotNull(nodeIdent);
        checkNotNull(nodeManagement);
        return put(ActionResponse.class, uri("/nodes/%s/state/power", nodeIdent)).entity(nodeManagement).execute();
    }

    @Override
    public ActionResponse setBootDevice(String nodeIdent, NodeManagement nodeManagement) {
        checkNotNull(nodeIdent);
        checkNotNull(nodeManagement);
        return put(ActionResponse.class, uri("/nodes/%s/management/boot_device", nodeIdent)).entity(nodeManagement).execute();
    }

    @Override
    public ActionResponse setConsole(String nodeIdent, NodeManagement nodeManagement) {
        checkNotNull(nodeIdent);
        checkNotNull(nodeManagement);
        return put(ActionResponse.class, uri("/nodes/%s/states/console", nodeIdent)).entity(nodeManagement).execute();
    }

    @Override
    public ActionResponse setProvision(String nodeIdent, NodeManagement nodeManagement) {
        checkNotNull(nodeIdent);
        checkNotNull(nodeManagement);
        return put(ActionResponse.class, uri("/nodes/%s/states/provision", nodeIdent)).entity(nodeManagement).execute();
    }

    @Override
    public ActionResponse setProvision(String nodeIdent, NodeManagement nodeManagement, Map<String, Object> headers) {
        checkNotNull(nodeIdent);
        checkNotNull(nodeManagement);
        checkNotNull(headers);
        return put(ActionResponse.class, uri("/nodes/%s/states/provision", nodeIdent)).entity(nodeManagement).headers(headers).execute();
    }

    @Override
    public NodeValidate validate(String nodeIdent) {
        checkNotNull(nodeIdent);
        return get(BareMetalNodeValidate.class, uri("/nodes/%s/validate", nodeIdent)).execute();
    }

    @Override
    public NodeSupportedBootDevices getSupportedBootDevices(String nodeIdent) {
        checkNotNull(nodeIdent);
        return get(BareMetalNodeSupportedBootDevices.class, uri("/v1/nodes/%s/management/boot_device/supported", nodeIdent)).execute();
    }
}
