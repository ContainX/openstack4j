package org.openstack4j.openstack.telemetry.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.telemetry.Meter;

import java.util.List;

/**
 * Created by eandgya on 3/2/17.
 */
public class GnocchiMetricsMetadata {
    private static final long serialVersionUID = 1L;

    @JsonProperty("archive_policy")
    private ArchivePolicy archivePolicy;

    @JsonProperty("created_by_project_id")
    private String createdByProjectId;

    @JsonProperty("created_by_user_id")
    private String createdByUserId;

    @JsonProperty
    private String id;

    @JsonProperty
    private String name;

    @JsonProperty("resource_id")
    private String resourceId;

    @JsonProperty
    private String unit;


    public static class ArchivePolicy {

        @JsonProperty("aggregation_methods")
        public List<String> aggregationMethods;

        @JsonProperty("back_window")
        public int backWindow;

        public List <Definition> definition;

        public String name;


        public static class Definition {
            @JsonProperty
            public String granularity;

            @JsonProperty
            public int points;

            @JsonProperty
            public String timespan;

            @Override
            public String toString() {
                return "Definition{" +
                        "granularity='" + granularity + '\'' +
                        ", points=" + points +
                        ", timespan='" + timespan + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "ArchivePolicy{" +
                    "aggregationMethods=" + aggregationMethods +
                    ", backWindow=" + backWindow +
                    ", definition=" + definition +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GnocchiMetricsMetadata{" +
                "archivePolicy=" + archivePolicy +
                ", createdByProjectId='" + createdByProjectId + '\'' +
                ", createdByUserId='" + createdByUserId + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", resourceId='" + resourceId + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }
}
