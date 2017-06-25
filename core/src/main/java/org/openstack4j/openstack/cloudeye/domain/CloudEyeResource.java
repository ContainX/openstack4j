package org.openstack4j.openstack.cloudeye.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import org.openstack4j.model.cloudeye.QuotaType;
import org.openstack4j.model.cloudeye.Resource;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("quotas")
public class CloudEyeResource implements Resource {
    QuotaType type;
    Integer used;
    String unit;
    Integer quota;
}
