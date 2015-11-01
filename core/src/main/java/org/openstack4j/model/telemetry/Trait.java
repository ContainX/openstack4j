package org.openstack4j.model.telemetry;

/**
 * A single measurement for a given meter and resource.
 *
 * @author Miroslav Lacina
 */
public interface Trait {

    String getName();

    String getType();

    String getValue();

}
