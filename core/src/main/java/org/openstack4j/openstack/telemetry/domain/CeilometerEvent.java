package org.openstack4j.openstack.telemetry.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.telemetry.Event;
import org.openstack4j.model.telemetry.Trait;
import org.openstack4j.model.telemetry.TraitDescription;

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

    public static class CeilometerTrait implements Trait {

        @JsonProperty("name")
        private String name;
        @JsonProperty("type")
        private String type;
        @JsonProperty("value")
        private String value;

        /**
         * {@inheritDoc}
         */
        @Override
        public String getName() {
            return name;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getType() {
            return type;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getValue() {
            return value;
        }

        /**
         * {@inheritDoc}
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * {@inheritDoc}
         */
        public void setType(String type) {
            this.type = type;
        }

        /**
         * {@inheritDoc}
         */
        public void setValue(String value) {
            this.value = value;
        }

    }

    public static class CeilometerTraitDescription implements TraitDescription {

        @JsonProperty("name")
        private String name;
        @JsonProperty("type")
        private String type;

        /**
         * {@inheritDoc}
         */
        @Override
        public String getName() {
            return name;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getType() {
            return type;
        }

        /**
         * {@inheritDoc}
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * {@inheritDoc}
         */
        public void setType(String type) {
            this.type = type;
        }

    }

}
