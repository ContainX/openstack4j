package org.openstack4j.openstack.cloudeye.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.openstack4j.model.cloudeye.Metric;
import org.openstack4j.openstack.common.ListResult;

import java.util.List;

@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CloudEyeMetric implements Metric {

    private static final long serialVersionUID = -4166055531397254389L;

    String namespace;
    List<CloudEyeMetricDemension> dimensions;
    @JsonProperty("metric_name")
    String metricName;
    String unit;

    public static class CloudEyeMetrics extends ListResult<CloudEyeMetric> {

        private static final long serialVersionUID = 2211086062776417518L;

        @JsonProperty("metrics")
        protected List<CloudEyeMetric> list;

        @Override
        public List<CloudEyeMetric> value() {
            return list;
        }
    }
}
