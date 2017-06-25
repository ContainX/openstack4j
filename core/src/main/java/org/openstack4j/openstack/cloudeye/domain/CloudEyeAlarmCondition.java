package org.openstack4j.openstack.cloudeye.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.openstack4j.model.cloudeye.Condition;


@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CloudEyeAlarmCondition implements Condition {
    private static final long serialVersionUID = 5719142668031530589L;
    Integer period;
    String filter;
    @JsonProperty("comparison_operator")
    String comparisonOperator;
    Number value;
    String unit;
    Integer count;
}

