package com.huawei.openstack4j.openstack.cloudeye.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.cloudeye.Metric;
import com.huawei.openstack4j.model.cloudeye.MetricData;
import com.huawei.openstack4j.openstack.common.ListResult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CloudEyeMetricData implements MetricData {

    private static final long serialVersionUID = -4166055531397254389L;
    Metric metric;
    String unit;
    Number ttl;
    @JsonProperty("collect_time")
    Date collectTime;
    Number value;
    ValueType type;


    public static class CloudEyeMetrics extends ListResult<CloudEyeMetricData> {

        private static final long serialVersionUID = 2211086062776417518L;

        @JsonProperty("metrics")
        protected List<CloudEyeMetricData> list;

        @Override
        public List<CloudEyeMetricData> value() {
            return list;
        }
    }
}
