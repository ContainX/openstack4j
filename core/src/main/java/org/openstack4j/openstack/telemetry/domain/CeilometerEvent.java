package org.openstack4j.openstack.telemetry.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.telemetry.Event;
import org.openstack4j.model.telemetry.Trait;

import java.util.List;

/**
 * A Meter is a category of Measurement
 *
 * @author Miroslav Lacina
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class CeilometerEvent implements Event {

	private static final long serialVersionUID = 1L;

	@JsonProperty("event_type")
	private String eventType;
    @JsonProperty("generated")
    private String generated;
    @JsonProperty("message_id")
    private String messageId;
    @JsonProperty("traits")
    private List<CeilometerTrait> traits;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getEventType() {
        return eventType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getGenerated() {
        return generated;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessageId() {
        return messageId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Trait> getTraits() {
        return traits;
    }

    /**
     * {@inheritDoc}
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    /**
     * {@inheritDoc}
     */
    public void setGenerated(String generated) {
        this.generated = generated;
    }

    /**
     * {@inheritDoc}
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    /**
     * {@inheritDoc}
     */
    public void setTraits(List<CeilometerTrait> traits) {
        this.traits = traits;
    }

}
