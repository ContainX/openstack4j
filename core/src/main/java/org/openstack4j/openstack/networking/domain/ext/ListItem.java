package org.openstack4j.openstack.networking.domain.ext;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ListItem {
    @JsonProperty("id")
    String id;

    public ListItem(){
    }

    public ListItem(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }
}
