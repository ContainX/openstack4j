package org.openstack4j.model.artifact;

import org.openstack4j.model.ModelEntity;

/**
 * Created by vadavi on 18-01-2017.
 */
public interface Template extends ModelEntity {

    public String getMd5();

    public String getSha256();

    public String getContentType();

    public Boolean getExternal();

    public String getUrl();

    public String getSha1();

    public String getStatus();
}
