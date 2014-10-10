package org.openstack4j.openstack.logging;

/**
 * OpenStack4j Logging Abstraction Layer. Logging implementations are configurable by adding the desired OpenStack logging extension (SL4J, Log4j, etc).  By default
 * a Default Logging module is associated and only logs Error based messages.  This can also be changed to Console based logging if a logging extension is not
 * in the classpath.
 * 
 * @author Jeremy Unruh
 */
public interface Logger {

    /**
     * Check whether this category is enabled for the ERROR Level.
     * 
     * @return boolean - <code>true</code> if this category is enabled for level ERROR
     */
    boolean isErrorEnabled();
    
    /**
     * Check whether this category is enabled for the WARN Level.
     * 
     * @return boolean - <code>true</code> if this category is enabled for level WARN
     */
    boolean isWarnEnabled();
    
    /**
     * Check whether this category is enabled for the INFO Level.
     * 
     * @return boolean - <code>true</code> if this category is enabled for level INFO
     */
    boolean isInfoEnabled();
    
    /**
     * Check whether this category is enabled for the DEBUG Level.
     * 
     * @return boolean - <code>true</code> if this category is enabled for level DEBUG
     */
    boolean isDebugEnabled();
    
    /**
     * Check whether this category is enabled for the TRACE Level.
     * 
     * @return boolean - <code>true</code> if this category is enabled for level TRACE
     */
    boolean isTraceEnabled();
    
    /**
     * @return the current category this Logger is associated with
     */
    String getCategory();

    
    void trace(String message, Object... args);

    void debug(String message, Object... args);

    void info(String message, Object... args);

    void warn(String message, Object... args);

    void warn(Throwable throwable, String message, Object... args);

    void error(String message, Object... args);

    void error(Throwable throwable, String message, Object... args);
 }
