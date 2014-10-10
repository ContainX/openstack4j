package org.openstack4j.plugins.slf4j;

import org.openstack4j.openstack.logging.Logger;
import org.openstack4j.openstack.logging.internal.BaseLogger;

/**
 * OpenStack4j Plugin - SLF4J implementation of {@link Logger}.
 * 
 * @author Jeremy Unruh
 */
public class SLF4JLogger extends BaseLogger {

    static {
        org.slf4j.LoggerFactory.getILoggerFactory();
     }
     
     private final org.slf4j.Logger logger;
     private final String category;
    
    SLF4JLogger(String category, org.slf4j.Logger logger) {
        this.category = category;
        this.logger = logger;
    }
    
    @Override
    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    @Override
    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    @Override
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    @Override
    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    protected void logError(String message, Throwable e) {
        logger.error(message, e);
    }

    @Override
    protected void logError(String message) {
        logger.error(message);
    }

    @Override
    protected void logWarn(String message, Throwable e) {
        logger.warn(message, e);
    }

    @Override
    protected void logWarn(String message) {
        logger.warn(message);
    }

    @Override
    protected void logInfo(String message) {
        logger.info(message);
    }

    @Override
    protected void logDebug(String message) {
        logger.debug(message);
    }

    @Override
    protected void logTrace(String message) {
        logger.trace(message);
    }
}
