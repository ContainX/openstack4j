package org.openstack4j.openstack.common.functions;

import com.google.common.base.Function;

/**
 * Removes a trailing version and appends the specified version to an endpoint URL
 * 
 * @author Jeremy Unruh
 */
public class EnforceVersionToURL implements Function<String, String> {

    private final String version;
    
    private EnforceVersionToURL(String version) {
        this.version = version;
    }
    
    public static EnforceVersionToURL instance(String version) {
        return new EnforceVersionToURL(version);
    }
    
    
    @Override
    public String apply(String input) {
        return RemoveVersionFromURL.INSTANCE.apply(input).concat(version);
    }
    

}
