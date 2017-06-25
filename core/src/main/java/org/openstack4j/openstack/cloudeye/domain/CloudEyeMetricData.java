package org.openstack4j.openstack.cloudeye.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import org.openstack4j.model.cloudeye.Metric;
import org.openstack4j.model.cloudeye.MetricData;
import org.openstack4j.openstack.common.ListResult;

import java.util.Date;
import java.util.List;

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
