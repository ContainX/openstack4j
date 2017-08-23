package com.huawei.openstack4j.openstack.cloudeye.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.huawei.openstack4j.model.cloudeye.DataPoint;

import lombok.*;

import java.util.Date;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CloudEyeMetricDatapoint implements DataPoint {
    private static final long serialVersionUID = -4256727437488902455L;

    Number average;
    Number variance;
    Number min;
    Number max;
    Date timestamp;
    String unit;

}
