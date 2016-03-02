package org.openstack4j.model.trove.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.trove.DatabaseCreate;

/**
 * Created by cp16net on 2/15/16.
 */
public interface DatabaseBuilder extends Buildable.Builder<DatabaseBuilder, DatabaseCreate> {
    DatabaseBuilder name(String name);
    DatabaseBuilder addCharacterSet(String characterSet);
    DatabaseBuilder addCollate(String collate);
}
