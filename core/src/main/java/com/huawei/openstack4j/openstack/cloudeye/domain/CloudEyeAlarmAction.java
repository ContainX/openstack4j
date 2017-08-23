package com.huawei.openstack4j.openstack.cloudeye.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.huawei.openstack4j.model.cloudeye.Alarm;
import com.huawei.openstack4j.model.cloudeye.AlarmAction;
import com.huawei.openstack4j.model.cloudeye.AlarmType;

import lombok.*;

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
