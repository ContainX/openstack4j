package org.openstack4j.model.telemetry;

import org.openstack4j.model.ModelEntity;

import java.util.List;

/**
 * A single measurement for a given meter and resource.
 * 
 * @author Miroslav Lacina
 */
public interface Event extends ModelEntity {

    String getEventType();

    String getGenerated();

    String getMessageId();

    List<? extends Trait> getTraits();

}
