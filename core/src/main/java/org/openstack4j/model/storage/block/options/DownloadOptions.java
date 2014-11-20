package org.openstack4j.model.storage.block.options;

import java.util.List;

import org.openstack4j.model.common.functions.RangesToHeaderNameValue;
import org.openstack4j.model.common.header.HeaderNameValue;
import org.openstack4j.model.common.header.IfCondition;
import org.openstack4j.model.common.header.Range;

import com.google.common.collect.Lists;

/**
 * Download options used to determine how the data is returned or if it is returned depending on various conditional
 * options
 * 
 * @author Jeremy Unruh
 */
public class DownloadOptions {

    List<HeaderNameValue> headers = Lists.newArrayList();
    
    private DownloadOptions() { 
    }
    
    public static DownloadOptions create() {
        return new DownloadOptions();
    }
    
    /**
     * Download select ranges of bytes 
     * 
     * @param ranges one or more Range objects
     * @return DownloadOptions for method chaining
     */
    public DownloadOptions range(Range... ranges) {
        HeaderNameValue h = RangesToHeaderNameValue.INSTANCE.apply(ranges);
        if (h != null)
            headers.add(h);
        return this;
    }
    
    /**
     * Adds one or more If based conditions to the header chain to offer conditional matching 
     * before the data is streamed
     * 
     * @param condition one or more IfCondition objects
     * @return DownloadOptions for method chaining
     * @see http://www.ietf.org/rfc/rfc2616.txt
     */
    public DownloadOptions conditions(IfCondition... condition) {
        if (condition != null) {
            for (IfCondition c : condition)
                headers.add(c.toHeader());
        }
        return this;
    }
    
    /**
     * @return all headers configured from this options object
     */
    public List<HeaderNameValue> getHeaders() {
        return headers;
    }
}
