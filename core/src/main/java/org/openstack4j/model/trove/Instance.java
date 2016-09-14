package org.openstack4j.model.trove;

import java.util.Date;

public interface Instance {

    int getVolumeSize();

    String getVolumeType();

    Flavor getFlavor();

    String getName();

    Date getCreated();

    Date getUpdated();

    String getHostname();

    String getId();

    String getStatus();

}
