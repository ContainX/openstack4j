package org.openstack4j.openstack.storage.object.domain;

/**
 * Common Object Storage (Swift) headers
 * 
 * @author Jeremy Unruh
 */
public final class SwiftHeaders {

    // Account Headers
    public static final String ACCOUNT_METADATA_PREFIX = "X-Account-Meta-";
    public static final String ACCOUNT_REMOVE_METADATA_PREFIX = "X-Remove-Account-Meta-";
    public static final String ACCOUNT_TEMPORARY_URL_KEY = ACCOUNT_METADATA_PREFIX + "Temp-Url-Key";
    public static final String ACCOUNT_BYTES_USED = "X-Account-Bytes-Used";
    public static final String ACCOUNT_CONTAINER_COUNT = "X-Account-Container-Count";
    public static final String ACCOUNT_OBJECT_COUNT = "X-Account-Object-Count";
    
    private SwiftHeaders() {
    }
}
