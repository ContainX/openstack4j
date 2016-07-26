package org.openstack4j.openstack.networking.domain.ext;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

@JsonAutoDetect
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

    @Override
    public String toString(){
        return Objects.toStringHelper(this)
                .add("id", id)
                .toString();
    }
}
