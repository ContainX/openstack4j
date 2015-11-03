package org.openstack4j.model.telemetry;

/**
 * A description of a trait, with no associated value.
 *
 * @author Miroslav Lacina
 */
public interface TraitDescription {

    /**
     * @return name of Trait
     */
    String getName();

    /**
     * @return data type of Trait
     */
    String getType();

}
