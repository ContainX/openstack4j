package org.openstack4j.openstack.image.v2;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.openstack4j.model.image.v2.PatchOperation;
import org.openstack4j.model.image.v2.builder.PatchOperationBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GlancePatchOperation implements PatchOperation {
    private OperationType op;
    private String path;
    private Object value;

    public GlancePatchOperation() {}

    public GlancePatchOperation(OperationType op, String path, Object value) {
        this.op = op;
        this.path = path;
        this.value = value;
    }

    public GlancePatchOperation(OperationType op, String path) {
        this.op = op;
        this.path = path;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OperationType getOp() {
        return op;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPath() {
        return path;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getValue() {
        return value;
    }

    public void setOp(OperationType op) {
        this.op = op;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PatchOperationBuilder toBuilder() {
        return new PatchOperationConcreteBuilder(this);
    }

    public static PatchOperationBuilder builder() {
        return new PatchOperationConcreteBuilder();
    }

    public static class PatchOperationConcreteBuilder implements PatchOperationBuilder {
        GlancePatchOperation m;

        public PatchOperationConcreteBuilder() {
            this(new GlancePatchOperation());
        }

        public PatchOperationConcreteBuilder(GlancePatchOperation m) {
            this.m = m;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public PatchOperationBuilder operationType(OperationType operationType) {
            m.op = operationType;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public PatchOperationBuilder path(String path) {
            m.path = path;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public PatchOperationBuilder value(Object value) {
            m.value = value;
            return this;
        }

        @Override
        public PatchOperation build() {
            return m;
        }

        @Override
        public PatchOperationBuilder from(PatchOperation in) {
            m = (GlancePatchOperation) in;
            return this;
        }
    }
}
