package org.openstack4j.openstack.common.functions;

import java.util.List;

import com.google.common.base.Function;

/**
 * A Function which returns one entry from a List or null
 * @author Jeremy Unruh
 * 
 * @param <T> The return Type
 */
public class OneOrNull<T> implements Function<List<T>, T> {

    public static <T> OneOrNull<T> create() {
        return new OneOrNull<T>();
    }
     
    @Override
    public T apply(List<T> input) {
        if (input != null && input.size() > 0)
            return input.get(0);
        return null;
    }

}
