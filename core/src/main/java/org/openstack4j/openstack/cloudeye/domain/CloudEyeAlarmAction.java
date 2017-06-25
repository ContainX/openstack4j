package org.openstack4j.openstack.cloudeye.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.openstack4j.model.cloudeye.Alarm;
import org.openstack4j.model.cloudeye.AlarmAction;
import org.openstack4j.model.cloudeye.AlarmType;

import java.util.List;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CloudEyeAlarmAction implements AlarmAction{
    private static final long serialVersionUID = 5604518526573924452L;

    AlarmType type;
    List<String> notificationlist;
}
