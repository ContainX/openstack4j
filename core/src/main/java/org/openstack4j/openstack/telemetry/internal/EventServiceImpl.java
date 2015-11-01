package org.openstack4j.openstack.telemetry.internal;

import org.openstack4j.api.telemetry.EventService;
import org.openstack4j.model.telemetry.*;
import org.openstack4j.openstack.telemetry.domain.CeilometerEvent;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Provides Measurements against Meters within an OpenStack deployment
 *
 * @author Miroslav Lacina
 */
public class EventServiceImpl extends BaseTelemetryServices implements EventService {

    private static final String FIELD = "q.field";
    private static final String OPER = "q.op";
    private static final String VALUE = "q.value";
    private static final String LIMIT = "limit";

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Event> list(EventQuery eventQuery) {
        Invocation<CeilometerEvent[]> invocation = get(CeilometerEvent[].class, uri("/events"));
        if (eventQuery != null && !eventQuery.getCriteriaParams().isEmpty()) {
            for (EventQuery.NameOpValue c : eventQuery.getCriteriaParams()) {
                invocation.param(FIELD, c.getField());
                invocation.param(OPER, c.getOperator().getQueryValue());
                invocation.param(VALUE, c.getValue());
            }
        }

        CeilometerEvent[] events = invocation.execute();
        return wrapList(events);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Event> list(EventQuery eventQuery, int limit) {
        Invocation<CeilometerEvent[]> invocation = get(CeilometerEvent[].class, uri("/events"));
        if (eventQuery != null && !eventQuery.getCriteriaParams().isEmpty()) {
            for (EventQuery.NameOpValue c : eventQuery.getCriteriaParams()) {
                invocation.param(FIELD, c.getField());
                invocation.param(OPER, c.getOperator().getQueryValue());
                invocation.param(VALUE, c.getValue());
            }
        }
        invocation.param(LIMIT, limit);

        CeilometerEvent[] events = invocation.execute();
        return wrapList(events);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event get(String messageId) {
        checkNotNull(messageId);
        return get(CeilometerEvent.class, uri("/events/%s", messageId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> listEventTypes() {
        String[] eventTypes = get(String[].class, uri("/event_types")).execute();
        return wrapList(eventTypes);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends TraitDescription> listTraitDescriptions(String eventType) {
        checkNotNull(eventType);
        TraitDescription[] traitDescriptions = get(TraitDescription[].class, uri("/event_types/%s/traits", eventType)).execute();
        return wrapList(traitDescriptions);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Trait> listTraits(String eventType, String traitName) {
        checkNotNull(eventType);
        checkNotNull(traitName);
        Trait[] traits = get(Trait[].class, uri("/event_types/%s/traits/%s", eventType, traitName)).execute();
        return wrapList(traits);
    }

}
