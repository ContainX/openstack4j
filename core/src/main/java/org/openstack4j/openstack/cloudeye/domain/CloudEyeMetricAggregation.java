package org.openstack4j.openstack.cloudeye.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.openstack4j.model.cloudeye.DataPoint;
import org.openstack4j.model.cloudeye.MetricAggregation;

import java.util.List;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CloudEyeMetricAggregation implements MetricAggregation {
    List<CloudEyeMetricDatapoint> datapoints;
    @JsonProperty("metric_name")
    String metricName;
}
