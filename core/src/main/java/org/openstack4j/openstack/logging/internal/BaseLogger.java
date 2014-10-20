package org.openstack4j.openstack.logging.internal;

import org.openstack4j.openstack.logging.Logger;

/**
 * Base Logger implementation which handles String formatting for messages
 * 
 * @author Jeremy Unruh
 */
public abstract class BaseLogger implements Logger {

    protected abstract void logError(String message, Throwable e);

    protected abstract void logError(String message);

    protected abstract void logWarn(String message, Throwable e);

    protected abstract void logWarn(String message);

    protected abstract void logInfo(String message);

    protected abstract void logDebug(String message);

    protected abstract void logTrace(String message);

    /**
     * {@inheritDoc}
     */
    public void trace(String message, Object... args) {
        if (isTraceEnabled())
            logTrace(formatArguments(message, args));
    }

    /**
     * {@inheritDoc}
     */
    public void debug(String message, Object... args) {
        if (isDebugEnabled())
            logDebug(formatArguments(message, args));
    }

    /**
     * {@inheritDoc}
     */
    public void info(String message, Object... args) {
        if (isInfoEnabled())
            logInfo(formatArguments(message, args));
    }

    /**
     * {@inheritDoc}
     */
    public void warn(String message, Object... args) {
        if (isWarnEnabled())
            logWarn(formatArguments(message, args));
    }

    /**
     * {@inheritDoc}
     */
    public void warn(Throwable e, String message, Object... args) {
        if (isWarnEnabled())
            logWarn(formatArguments(message, args), e);
    }

    /**
     * {@inheritDoc}
     */
    public void error(String message, Object... args) {
        if (isErrorEnabled())
            logError(formatArguments(message, args));
    }

    /**
     * {@inheritDoc}
     */
    public void error(Throwable e, String message, Object... args) {
        if (isErrorEnabled())
            logError(formatArguments(message, args), e);
    }

    private String formatArguments(String message, Object... args) {
        return args.length == 0 ? message : String.format(message, args);
    }
}
