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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GroupService group() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public L2policyService l2Policy() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public L3policyService l3Policy() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public NatPoolService natPool() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public NetworkService networkService() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PolicyActionService policyAction() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PolicyRuleService policyRule() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PolicyTargetService policyTarget() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PolicyClassifierService policyClassifier() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServicechainService servicechain() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServiceProfileService serviceProfile() {
        // TODO Auto-generated method stub
        return null;
    }

}
