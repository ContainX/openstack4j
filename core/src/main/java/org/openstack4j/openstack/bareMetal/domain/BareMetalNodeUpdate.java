package org.openstack4j.openstack.bareMetal.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.JsonNode;
import org.openstack4j.model.bareMetal.NodeUpdate;
import org.openstack4j.model.bareMetal.builder.NodeUpdateBuilder;
import org.openstack4j.openstack.image.v2.domain.PatchOperation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * the model for update bare metal node
 *
 * @author zhangliang
 */
public class BareMetalNodeUpdate implements NodeUpdate {

    List<PatchOperation> ops = new ArrayList<>();

    public BareMetalNodeUpdate(){

    }

    public BareMetalNodeUpdate(JsonNode value) {
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

    public BareMetalNodeUpdate(List<PatchOperation> ops) {
        this.ops = ops;
    }

    @Override
    @JsonValue
    public List<PatchOperation> getOps() {
        return ops;
    }

    @Override
    public NodeUpdateBuilder toBuilder() {
        return new NodeUpdateConcreteBuilder(this);
    }

    public static NodeUpdateBuilder builder(){
        return new NodeUpdateConcreteBuilder();
    }

    public static class NodeUpdateConcreteBuilder implements NodeUpdateBuilder {

        private BareMetalNodeUpdate m;

        public NodeUpdateConcreteBuilder(){
            this(new BareMetalNodeUpdate());
        }

        public NodeUpdateConcreteBuilder(BareMetalNodeUpdate m){
            this.m = m;
        }

        @Override
        public NodeUpdateBuilder ops(List<PatchOperation> ops) {
            m.ops = ops;
            return this;
        }

        @Override
        public NodeUpdateBuilder ops(PatchOperation op) {
            if (m.ops == null) m.ops = new ArrayList<>();
            m.ops.add(op);
            return this;
        }

        @Override
        public NodeUpdate build() {
            return m;
        }

        @Override
        public NodeUpdateBuilder from(NodeUpdate in) {
            m = (BareMetalNodeUpdate) in;
            return this;
        }
    }

}
