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
    private static final String JSON_EVENT_TYPES = "/telemetry/event-types.json";
    private static final String JSON_TRAIT_DESCRIPTIONS = "/telemetry/trait-descriptions.json";
    private static final String JSON_TRAITS = "/telemetry/traits.json";

    @Override
    protected Service service() {
        return Service.TELEMETRY;
    }

    @Test
    public void listTest() throws IOException {
        respondWith(JSON_EVENTS);

        List<? extends Event> events = osv3().telemetry().events().list(null);
        assertEquals(events.size(), 5);

        Event event = events.get(0);
        assertEquals(event.getEventType(), "image.create");
        assertEquals(event.getGenerated(), "2015-11-02T15:34:41.795313");
        assertEquals(event.getMessageId(), "abd307a1-d4a3-4eae-ab89-6d623e27bebf");
        assertEquals(event.getTraits().size(), 1);
        Trait trait = event.getTraits().get(0);
        assertEquals(trait.getName(), "service");
        assertEquals(trait.getValue(), "image.localhost");

    }

    @Test
    public void getTest() throws IOException {
        respondWith(JSON_EVENT);

        Event event = osv3().telemetry().events().get("adeda2eb-31e5-4908-a7dd-7a154abed468");

        assertEquals(event.getEventType(), "image.upload");
        assertEquals(event.getGenerated(), "2015-11-02T15:34:42.993281");
        assertEquals(event.getMessageId(), "adeda2eb-31e5-4908-a7dd-7a154abed468");
        assertEquals(event.getTraits().size(), 8);
        Trait trait = event.getTraits().get(0);
        assertEquals(trait.getName(), "created_at");
        assertEquals(trait.getValue(), "2015-11-02T15:34:41.000000");
    }

    @Test
    public void listEventTypesTest() throws IOException {
        respondWith(JSON_EVENT_TYPES);

        List<String> eventTypes = osv3().telemetry().events().listEventTypes();
        assertEquals(eventTypes.size(), 46);

        assertEquals(eventTypes.get(0), "compute.instance.create.end");
        assertEquals(eventTypes.get(1), "compute.instance.create.error");
    }

    @Test
    public void listTraitDescriptionsTest() throws IOException {
        respondWith(JSON_TRAIT_DESCRIPTIONS);

        List<? extends TraitDescription> traitDescriptions
                = osv3().telemetry().events().listTraitDescriptions("image.upload");
        assertEquals(traitDescriptions.size(), 8);

        assertEquals(traitDescriptions.get(0).getName(), "created_at");
        assertEquals(traitDescriptions.get(0).getType(), "string");
        assertEquals(traitDescriptions.get(1).getName(), "name");
        assertEquals(traitDescriptions.get(1).getType(), "string");
        assertEquals(traitDescriptions.get(2).getName(), "project_id");
        assertEquals(traitDescriptions.get(2).getType(), "string");
    }

    @Test
    public void listTraitsTest() throws IOException {
        respondWith(JSON_TRAITS);

        List<? extends Trait> traits = osv3().telemetry().events().listTraits("image.upload", "service");
        assertEquals(traits.size(), 5);

        assertEquals(traits.get(0).getName(), "service");
        assertEquals(traits.get(0).getType(), "string");
        assertEquals(traits.get(0).getValue(), "image.localhost");
        assertEquals(traits.get(1).getName(), "service");
        assertEquals(traits.get(1).getType(), "string");
        assertEquals(traits.get(1).getValue(), "image.localhost");
        assertEquals(traits.get(2).getName(), "service");
        assertEquals(traits.get(2).getType(), "string");
        assertEquals(traits.get(2).getValue(), "image.localhost");
    }

}
