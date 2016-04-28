package org.openstack4j.openstack.gbp.internal;

import org.openstack4j.api.Apis;
import org.openstack4j.api.gbp.ExternalPolicyService;
import org.openstack4j.api.gbp.ExternalSegmentService;
import org.openstack4j.api.gbp.GbpService;
import org.openstack4j.api.gbp.GroupService;
import org.openstack4j.api.gbp.L2policyService;
import org.openstack4j.api.gbp.L3policyService;
import org.openstack4j.api.gbp.NatPoolService;
import org.openstack4j.api.gbp.NetworkService;
import org.openstack4j.api.gbp.PolicyActionService;
import org.openstack4j.api.gbp.PolicyClassifierService;
import org.openstack4j.api.gbp.PolicyRuleService;
import org.openstack4j.api.gbp.PolicyRuleSetService;
import org.openstack4j.api.gbp.PolicyTargetService;
import org.openstack4j.api.gbp.ServiceProfileService;
import org.openstack4j.api.gbp.ServicechainService;
import org.openstack4j.openstack.networking.internal.BaseNetworkingServices;

public class GbpServiceImpl extends BaseNetworkingServices implements GbpService {

    @Override
    public ExternalPolicyService externalPolicy() {
        return Apis.get(ExternalPolicyService.class);
    }

    @Override
    public ExternalSegmentService externalSegment() {
        return Apis.get(ExternalSegmentService.class);
    }

    @Override
    public GroupService group() { 
        return Apis.get(GroupService.class);
    }

    @Override
    public L2policyService l2Policy() {
        return Apis.get(L2policyService.class);
    }

    @Override
    public L3policyService l3Policy() {
        return Apis.get(L3policyService.class);
    }

    @Override
    public NatPoolService natPool() {
        return Apis.get(NatPoolService.class);
    }

    @Override
    public NetworkService networkService() {
        return Apis.get(NetworkService.class);
    }

    @Override
    public PolicyActionService policyAction() {
        return Apis.get(PolicyActionService.class);
    }

    @Override
    public PolicyRuleService policyRule() {
        return Apis.get(PolicyRuleService.class);
    }
    @Override
    public PolicyRuleSetService policyRuleSet() {
        return Apis.get(PolicyRuleSetService.class);
    }
    @Override
    public PolicyTargetService policyTarget() {
        return Apis.get(PolicyTargetService.class);
    }

    @Override
    public PolicyClassifierService policyClassifier() {
        return Apis.get(PolicyClassifierService.class);
    }

    @Override
    public ServicechainService servicechain() {
        return Apis.get(ServicechainService.class);
    }

    @Override
    public ServiceProfileService serviceProfile() {
        return Apis.get(ServiceProfileService.class);
    }

}
