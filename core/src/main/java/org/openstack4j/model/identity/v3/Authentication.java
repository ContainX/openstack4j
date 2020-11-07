package org.openstack4j.model.identity.v3;

import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.ResourceEntity;

import java.util.List;

public interface Authentication extends ModelEntity {

    Identity getIdentity();

    Scope getScope();

    interface Identity {

        Password getPassword();

        Token getToken();

        List<String> getMethods();

        interface Password {

            User getUser();

            interface User extends ResourceEntity {

                Domain getDomain();

                String getPassword();

                interface Domain extends ResourceEntity {
                }
            }
        }

        interface Token {

            String getId();

        }

    }

    interface Scope {

        Project getProject();

        Domain getDomain();

        interface Project extends ResourceEntity {

            Domain getDomain();
        }

        interface Domain extends ResourceEntity {
        }

    }

}
