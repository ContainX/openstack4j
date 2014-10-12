package org.openstack4j.openstack.logging.internal;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * JDK Logger Option
 * 
 * @author Jeremy Unruh
 */
public class JDKLogger extends BaseLogger {

    private final Logger log;
    private final String category;
    
    public JDKLogger(String category) {
        this.log = Logger.getLogger(category);
        this.category = category;
    }
    
    @Override
    public boolean isErrorEnabled() {
        return log.isLoggable(Level.SEVERE);
    }

    @Override
    public boolean isWarnEnabled() {
        return log.isLoggable(Level.WARNING);
    }

    @Override
    public boolean isInfoEnabled() {
        return log.isLoggable(Level.INFO);
    }

    @Override
    public boolean isDebugEnabled() {
        return log.isLoggable(Level.FINE);
    }

    @Override
    public boolean isTraceEnabled() {
        return log.isLoggable(Level.FINEST);
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    protected void logError(String message, Throwable e) {
        log.log(Level.SEVERE, message, e);
    }

    @Override
    protected void logError(String message) {
        log.log(Level.SEVERE, message);
    }

    @Override
    protected void logWarn(String message, Throwable e) {
        log.log(Level.WARNING, message, e);
    }

    @Override
    protected void logWarn(String message) {
        log.log(Level.WARNING, message);
    }

    @Override
    protected void logInfo(String message) {
        log.log(Level.INFO, message);
    }

    @Override
    protected void logDebug(String message) {
        log.log(Level.FINE, message);
    }

    @Override
    protected void logTrace(String message) {
        log.log(Level.FINEST, message);
    }

}
