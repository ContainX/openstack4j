package org.openstack4j.model.common.functions;

import org.openstack4j.model.common.IdEntity;

import com.google.common.base.Function;

/**
 * A function which transforms an IdEntity into a String identifier
 * 
 * @author Jeremy Unruh
 */
public class IdEntityToString implements Function<IdEntity, String> {

    public static final IdEntityToString INSTANCE = new IdEntityToString();
    
    @Override
    public String apply(IdEntity input) {
        return input.getId();
    }

}
