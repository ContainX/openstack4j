package org.openstack4j.api.telemetry;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.telemetry.Event;
import org.openstack4j.model.telemetry.Trait;
import org.openstack4j.model.telemetry.TraitDescription;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertEquals;

@Test(suiteName="Event Tests")
public class EventTests extends AbstractTest {

    private static final String JSON_EVENTS = "/telemetry/events.json";
    private static final String JSON_EVENT = "/telemetry/event.json";

    @Override
    protected Service service() {
        return Service.TELEMETRY;
    }

    @Test
    public void listTest() throws IOException {
        respondWith(JSON_EVENTS);

        List<? extends Event> events = os().telemetry().events().list(null);
        assertEquals(3, events.size());

        Event event = events.get(0);
        assertEquals("compute.instance.create.end", event.getEventType());
        assertEquals("2015-01-01T12:30:59.123456", event.getGenerated());
        assertEquals("94834db1-8f1b-404d-b2ec-c35901f1b7f0", event.getMessageId());
        assertEquals(3, event.getTraits().size());
        Trait trait = event.getTraits().get(0);
        assertEquals("request_id", trait.getName());
        assertEquals("req-4e2d67b8-31a4-48af-bb2f-9df72a353a72", trait.getValue());

    }

    @Test
    public void getTest() throws IOException {
        respondWith(JSON_EVENT);

        Event event = os().telemetry().events().get("94834db1-8f1b-404d-b2ec-c35901f1b7f0");

        assertEquals("compute.instance.create.end", event.getEventType());
        assertEquals("2015-01-01T12:30:59.123456", event.getGenerated());
        assertEquals("94834db1-8f1b-404d-b2ec-c35901f1b7f0", event.getMessageId());
        assertEquals(3, event.getTraits().size());
        Trait trait = event.getTraits().get(0);
        assertEquals("request_id", trait.getName());
        assertEquals("req-4e2d67b8-31a4-48af-bb2f-9df72a353a72", trait.getValue());
    }

    @Test
    public void listEventTypesTest() throws IOException {
        respondWith(JSON_EVENTS);

        List<String> eventTypes = os().telemetry().events().listEventTypes();
        assertEquals(2, eventTypes.size());

        assertEquals("compute.instance.create.start", eventTypes.get(0));
        assertEquals("compute.instance.exists", eventTypes.get(1));
    }

    @Test
    public void listTraitDescriptionsTest() throws IOException {
        respondWith(JSON_EVENTS);

        List<? extends TraitDescription> traitDescriptions
                = os().telemetry().events().listTraitDescriptions("compute.instance.exists");
        assertEquals(3, traitDescriptions.size());

        assertEquals("request_id", traitDescriptions.get(0).getName());
        assertEquals("tenant_id", traitDescriptions.get(1).getName());
        assertEquals("service", traitDescriptions.get(2).getName());
    }

    @Test
    public void listTraitsTest() throws IOException {
        respondWith(JSON_EVENTS);

        List<? extends Trait> traits = os().telemetry().events().listTraits("compute.instance.exists", "request_id");
        assertEquals(2, traits.size());

        assertEquals("req-4e2d67b8-31a4-48af-bb2f-9df72a353a73", traits.get(0).getName());
        assertEquals("req-4e2d67b8-31a4-48af-bb2f-9df72a353a74", traits.get(1).getName());
    }

}
