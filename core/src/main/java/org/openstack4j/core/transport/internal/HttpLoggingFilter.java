package org.openstack4j.core.transport.internal;


/**
 * Handles turning Http Wire logging on/off for supported connectors.  Some connectors need have specific registration and use this class
 * to determine if wire logging is enabled
 * 
 * @author Jeremy Unruh
 */
public final class HttpLoggingFilter {

    private HttpLoggingFilter() { }
    
    public static void toggleLogging(boolean isEnabled) {
        System.getProperties().setProperty(HttpLoggingFilter.class.getName(), String.valueOf(isEnabled));
        System.setProperty("org.apache.commons.logging.Log","org.apache.commons.logging.impl.SimpleLog");
        System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http", isEnabled ? "DEBUG" : "WARN");
        System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire", "ERROR");
    }
    
    public static boolean isLoggingEnabled() {
        return Boolean.getBoolean(HttpLoggingFilter.class.getName());
    }
}
