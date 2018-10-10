package org.openstack4j.openstack.bareMetal.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.JsonNode;
import org.openstack4j.model.bareMetal.PortUpdate;
import org.openstack4j.model.bareMetal.builder.PortUpdateBuilder;
import org.openstack4j.openstack.image.v2.domain.PatchOperation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * the model for update bare metal
 *
 * @author zhangliang
 */
public class BareMetalPortUpdate implements PortUpdate {

    List<PatchOperation> ops = new ArrayList<>();

    public BareMetalPortUpdate(){

    }

    public BareMetalPortUpdate(JsonNode value) {
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

    public BareMetalPortUpdate(List<PatchOperation> ops) {
        this.ops = ops;
    }

    @Override
    @JsonValue
    public List<PatchOperation> getOps() {
        return ops;
    }

    @Override
    public PortUpdateBuilder toBuilder() {
        return new PortUpdateConcreteBuilder(this);
    }

    public static PortUpdateBuilder builder() {
        return new PortUpdateConcreteBuilder();
    }

    public static class PortUpdateConcreteBuilder implements PortUpdateBuilder {

        private BareMetalPortUpdate m;

        public PortUpdateConcreteBuilder(){
            this(new BareMetalPortUpdate());
        }

        public PortUpdateConcreteBuilder(BareMetalPortUpdate m){
            this.m = m;
        }

        @Override
        public PortUpdateBuilder ops(List<PatchOperation> ops) {
            m.ops = ops;
            return this;
        }

        @Override
        public PortUpdateBuilder ops(PatchOperation op) {
            if (m.ops == null) m.ops = new ArrayList<>();
            m.ops.add(op);
            return this;
        }

        @Override
        public PortUpdate build() {
            return m;
        }

        @Override
        public PortUpdateBuilder from(PortUpdate in) {
            m = (BareMetalPortUpdate) in;
            return this;
        }
    }
}
