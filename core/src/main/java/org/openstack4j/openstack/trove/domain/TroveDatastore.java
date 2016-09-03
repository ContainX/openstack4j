package org.openstack4j.openstack.trove.domain;

import java.util.List;

import org.openstack4j.model.trove.Datastore;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Model implementation for Datastore
 *
 * @author sumit gandhi
 */

@JsonRootName("datastore")
public class TroveDatastore implements Datastore {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String type;
    private String default_version;
    @JsonProperty("versions")
    private List<TroveDatastoreVersion> troveDatastoreVersionList;

    public String getDefault_version() {
        return default_version;
    }

    public void setDefault_version(String default_version) {
        this.default_version = default_version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<TroveDatastoreVersion> getTroveDatastoreVersionList() {
        return troveDatastoreVersionList;
    }

    public void setTroveDatastoreVersionList(List<TroveDatastoreVersion> troveDatastoreVersionList) {
        this.troveDatastoreVersionList = troveDatastoreVersionList;
    }

    public static class Datastores extends ListResult<TroveDatastore> {

        private static final long serialVersionUID = 1L;
        @JsonProperty("datastores")
        private List<TroveDatastore> troveDatastoreList;

        @Override
        protected List<TroveDatastore> value() {
            return troveDatastoreList;
        }

    }

}
