package org.openstack4j.api.telemetry;

import org.openstack4j.common.RestService;
import org.openstack4j.model.telemetry.Event;
import org.openstack4j.model.telemetry.EventCriteria;
import org.openstack4j.model.telemetry.Trait;
import org.openstack4j.model.telemetry.TraitDescription;

import java.util.List;

/**
 * Provides events within an OpenStack deployment
 * 
 * @author Miroslav Lacina
 */
public interface EventService extends RestService {

    List<? extends Event> list(EventCriteria eventCriteria);

    List<? extends Event> list(EventCriteria eventCriteria, int limit);

    Event get(String messageId);

    List<String> listEventTypes();

    List<? extends TraitDescription> listTraitDescriptions(String eventType);

    List<? extends Trait> listTraits(String eventType, String traitName);

}
