package org.openstack4j.model.gbp;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.common.Resource;
import org.openstack4j.model.gbp.builder.PolicyClassifierBuilder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Policy Classifier Model Entity
 * 
 * @author vinod borole
 */
public interface PolicyClassifier extends Buildable<PolicyClassifierBuilder>, Resource {
    
    /**
     * Is Policy classifier shared
     *
     * @return the true if shared and false if not shared
     */
    boolean isShared();

    /**
     * Gets the Protocol
     *
     * @return the Protocol
     */
    Protocol getProtocol();

    /**
     * Gets the Direction
     *
     * @return the Direction
     */
    Direction getDirection();

    /**
     * Gets the Port range
     *
     * @return the Port range
     */
    String getPortRange();

    /**
     * Gets the description
     *
     * @return the description
     */
    String getDescription();

    public enum Protocol{
        TCP,
        UDP,
        ICMP,
        HTTP,
        HTTPS,
        SMTP,
        DNS,
        FTP,
        ANY,
        UNRECOGNIZED;
        
        @JsonCreator
        public static Protocol forValue(String value) {
            if (value != null)
            {
                for (Protocol s : Protocol.values()) {
                    if (s.name().equalsIgnoreCase(value))
                        return s;
                }
            }
            return Protocol.UNRECOGNIZED;
        }
        
        @JsonValue
        public String value() {
            return name().toLowerCase();
        }
    }
    
    public enum Direction{
        IN,
        OUT,
        BI,
        UNRECOGNIZED;
        
        @JsonCreator
        public static Direction forValue(String value) {
            if (value != null)
            {
                for (Direction s : Direction.values()) {
                    if (s.name().equalsIgnoreCase(value))
                        return s;
                }
            }
            return Direction.UNRECOGNIZED;
        }
        
        @JsonValue
        public String value() {
            return name().toLowerCase();
        }
    }
    
    /**
     * Is Policy classifier shared
     *
     * @return the true if shared and false if not shared
     */
    boolean isShared();

    /**
     * Gets the Protocol
     *
     * @return the Protocol
     */
    Protocol getProtocol();

    /**
     * Gets the Direction
     *
     * @return the Direction
     */
    Direction getDirection();

    /**
     * Gets the Port range
     *
     * @return the Port range
     */
    String getPortRange();

    /**
     * Gets the description
     *
     * @return the description
     */
    String getDescription();

}
