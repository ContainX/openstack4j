package org.openstack4j.model.cloudeye;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.ModelEntity;

import java.util.List;

public interface Quota extends ModelEntity{
    List<? extends Resource> getResources();
}
