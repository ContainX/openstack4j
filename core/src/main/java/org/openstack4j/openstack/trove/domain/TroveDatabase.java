package org.openstack4j.openstack.trove.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.trove.Database;
import org.openstack4j.model.trove.builder.DatabaseBuilder;
import org.openstack4j.openstack.common.ListResult;

import java.util.List;

/**
 * Model implementation for Database
 *
 * @author sumit gandhi
 */

public class TroveDatabase implements Database {

    private String name;
    private String id;

    @Override
    public DatabaseBuilder toBuilder() {
        return null;
    }

    @Override
    public String getTenantId() {
        return null;
    }

    @Override
    public void setTenantId(String tenantId) {

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public static class Databases extends ListResult<TroveDatabase> {

        private static final long serialVersionUID = 1L;

        @JsonProperty("databases")
        private List<TroveDatabase> troveDatabaseList;

        public List<TroveDatabase> getTroveDatabaseList() {
            return troveDatabaseList;
        }

        public void setTroveDatabaseList(List<TroveDatabase> troveDatabaseList) {
            this.troveDatabaseList = troveDatabaseList;
        }

        @Override
        protected List<TroveDatabase> value() {
            return troveDatabaseList;
        }

    }

}
