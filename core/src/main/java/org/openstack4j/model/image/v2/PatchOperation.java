package org.openstack4j.model.image.v2;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openstack4j.common.Buildable;
import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.image.v2.builder.PatchOperationBuilder;

public interface PatchOperation extends ModelEntity, Buildable<PatchOperationBuilder> {

    public enum OperationType {
        ADD,
        REMOVE,
        REPLACE,
        UNKNOWN;

        @JsonCreator
        public static OperationType value(String v)
        {
            if (v == null) return UNKNOWN;
            try {
                return valueOf(v.toUpperCase());
            } catch (IllegalArgumentException e) {
                return UNKNOWN;
            }
        }

        @JsonValue
        public String value() {
            return name().toLowerCase();
        }
    }

    /**
     * Operation type of the json patch operation
     * @return operation type
     */
    OperationType getOp();

    /**
     * The path of the operation
     * @return path
     */
    String getPath();

    /**
     * The value of the operation
     * Required for OperationType.ADD, OperationType.REPLACE
     * @return
     */
    Object getValue();

}
