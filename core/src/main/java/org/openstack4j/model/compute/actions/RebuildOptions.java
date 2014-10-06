package org.openstack4j.model.compute.actions;

import java.util.Iterator;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * Options used to invoke the Rebuild Action on a Server
 *
 * @author Jeremy Unruh
 */
public final class RebuildOptions {

    private static final String OPT_FMT = "\"%s\": \"%s\"";
    
    private enum Option {
        IMAGE("imageRef"),
        NAME("name"),
        ADMIN_PASS("adminPass")
        ;
        private final String param;
        private Option(String param) { this.param = param; }
        
        public String getParam() {
            return param;
        }
    }
    
    private Map<Option, Object> options = Maps.newHashMap();
    
    private RebuildOptions() { }
    
    /**
     * @return a new RebuildOptions object
     */
    public static RebuildOptions create() {
        return new RebuildOptions();
    }
    
    /**
     * Can optionally set a new image to rebuild the server against.  This is the image identifier
     * 
     * @param imageId the image id to rebuild the server against
     * @return RebuildOptions
     */
    public RebuildOptions image(String imageId) {
        options.put(Option.IMAGE, imageId);
        return this;
    }
    
    /**
     * Can optionally change the name of the instance to a new {@code name}
     * 
     * @param name the new name of the server instance
     * @return RebuildOptions
     */
    public RebuildOptions name(String name) {
        options.put(Option.NAME, name);
        return this;
    }
    
    /**
     * Can optionally specify a new admin password to be used during the rebuild
     * 
     * @param adminPass the new admin password
     * @return RebuildOptions
     */
    public RebuildOptions adminPass(String adminPass) {
        options.put(Option.ADMIN_PASS, adminPass);
        return this;
    }
    
    /**
     * @return A JSON String representing this object
     */
    public String toJsonString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        Iterator<Option> it = options.keySet().iterator();
        while (it.hasNext()) {
            Option opt = it.next();
            sb.append(String.format(OPT_FMT, opt.getParam(), options.get(opt)));
            if (it.hasNext())
                sb.append(",\n");
        }
        sb.append("\n}");
        return sb.toString();
    }
    
}
