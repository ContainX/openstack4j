package org.openstack4j.openstack.trove.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.trove.DatabaseUser;
import org.openstack4j.openstack.common.ListResult;

import java.util.List;

/**
 * Model implementation for Database User
 *
 * @author sumit gandhi
 */

public class TroveDatabaseUser implements DatabaseUser {

    @JsonProperty("name")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("databases")
    List<TroveDatabase> troveDatabaseList;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<TroveDatabase> getTroveDatabaseList() {
        return troveDatabaseList;
    }

    public void setTroveDatabaseList(List<TroveDatabase> troveDatabaseList) {
        this.troveDatabaseList = troveDatabaseList;
    }

    public static class DatabaseUsers extends ListResult<TroveDatabaseUser> {

        private static final long serialVersionUID = 1L;
        @JsonProperty("users")
        private List<TroveDatabaseUser> troveDatabaseUserList;

        public List<TroveDatabaseUser> getTroveDatabaseUserList() {
            return troveDatabaseUserList;
        }

        public void setTroveDatabaseUserList(List<TroveDatabaseUser> troveDatabaseUserList) {
            this.troveDatabaseUserList = troveDatabaseUserList;
        }

        @Override
        protected List<TroveDatabaseUser> value() {
            return troveDatabaseUserList;
        }

    }

}
