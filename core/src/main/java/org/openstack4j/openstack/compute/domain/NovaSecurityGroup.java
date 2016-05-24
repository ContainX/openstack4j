package org.openstack4j.openstack.compute.domain;

import org.openstack4j.model.compute.SecurityGroup;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("security_groups")
public class NovaSecurityGroup implements SecurityGroup {

    private static final long serialVersionUID = 1L;
    private String name;
    
    @Override
    public String getName() {
        return name;
    }

}
