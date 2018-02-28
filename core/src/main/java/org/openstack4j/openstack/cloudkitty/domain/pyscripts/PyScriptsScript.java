package org.openstack4j.openstack.cloudkitty.domain.pyscripts;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.cloudkitty.builder.pyscripts.ScriptBuilder;
import org.openstack4j.model.cloudkitty.pyscripts.Script;
import org.openstack4j.openstack.common.ListResult;

import java.util.List;

public class PyScriptsScript implements Script {

    @JsonProperty("script_id")
    private String id;
    private String checksum;
    private String data;
    private String name;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getChecksum() {
        return checksum;
    }

    @Override
    public String getData() {
        return data;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ScriptBuilder toBuilder() {
        return new PyScriptsScriptConcreteBuilder(this);
    }

    public static ScriptBuilder builder() {
        return new PyScriptsScriptConcreteBuilder();
    }

    public static class PyScriptsScriptConcreteBuilder implements ScriptBuilder {

        private PyScriptsScript model;

        public PyScriptsScriptConcreteBuilder() {
            this(new PyScriptsScript());
        }

        public PyScriptsScriptConcreteBuilder(PyScriptsScript model) {
            this.model = model;
        }

        @Override
        public ScriptBuilder id(String id) {
            model.id = id;
            return this;
        }

        @Override
        public ScriptBuilder checksum(String checksum) {
            model.checksum = checksum;
            return this;
        }

        @Override
        public ScriptBuilder name(String name) {
            model.name = name;
            return this;
        }

        @Override
        public ScriptBuilder data(String data) {
            model.data = data;
            return this;
        }

        @Override
        public Script build() {
            return model;
        }

        @Override
        public ScriptBuilder from(Script in) {
            model = (PyScriptsScript) in;
            return this;
        }
    }

    public static class Scripts extends ListResult<PyScriptsScript> {

        @JsonProperty
        List<PyScriptsScript> scripts;

        @Override
        protected List<PyScriptsScript> value() {
            return scripts;
        }
    }
}
