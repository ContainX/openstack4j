package org.openstack4j.openstack.bareMetal.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.JsonNode;
import org.openstack4j.model.bareMetal.ChassisUpdate;
import org.openstack4j.model.bareMetal.builder.ChassisUpdateBuilder;
import org.openstack4j.openstack.image.v2.domain.PatchOperation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Bare Metal ChassisUpdate
 *
 * @author zhangliang
 */
public class BareMetalChassisUpdate implements ChassisUpdate {

    List<PatchOperation> ops = new ArrayList<>();

    public BareMetalChassisUpdate() {

    }

    public BareMetalChassisUpdate(JsonNode value) {
        if (value.isArray()) {
            for (Iterator<JsonNode> iterator = value.iterator(); iterator.hasNext(); ) {
                JsonNode next = iterator.next();
                iterator.remove();
                PatchOperation p = new PatchOperation(
                        PatchOperation.OperationType.value(next.get("op").textValue()),
                        next.get("path").textValue(),
                        next.get("value")
                );
                ops.add(p);
            }
        }
    }

    public BareMetalChassisUpdate(List<PatchOperation> ops) {
        this.ops = ops;
    }

    @Override
    @JsonValue
    public List<PatchOperation> getOps() {
        return ops;
    }

    @Override
    public ChassisUpdateBuilder toBuilder() {
        return new ChassisUpdateConcreteBuilder(this);
    }

    public static ChassisUpdateBuilder builder() {
        return new ChassisUpdateConcreteBuilder();
    }

    public static class ChassisUpdateConcreteBuilder implements ChassisUpdateBuilder {

        private BareMetalChassisUpdate m;

        public ChassisUpdateConcreteBuilder() {
            this(new BareMetalChassisUpdate());
        }

        public ChassisUpdateConcreteBuilder(BareMetalChassisUpdate m) {
            this.m = m;
        }

        @Override
        public ChassisUpdate build() {
            return m;
        }

        @Override
        public ChassisUpdateBuilder from(ChassisUpdate in) {
            m = (BareMetalChassisUpdate) in;
            return this;
        }

        @Override
        public ChassisUpdateBuilder ops(List<PatchOperation> ops) {
            m.ops = ops;
            return this;
        }

        @Override
        public ChassisUpdateBuilder ops(PatchOperation op) {
            if (m.ops == null) m.ops = new ArrayList<>();
            m.ops.add(op);
            return this;
        }
    }

}
