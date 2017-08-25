package com.huawei.openstack4j.model.cloudeye;

import java.util.List;

import com.huawei.openstack4j.model.ModelEntity;

public interface Quota extends ModelEntity{
	
    List<? extends Resource> getResources();
}
