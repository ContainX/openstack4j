package org.openstack4j.openstack.logging.internal;

import org.openstack4j.openstack.logging.Logger;

import com.google.common.base.Throwables;

/**
 * Console based Logger used as a fallback when no Logger extenions are present.  It only logs Warning and Error levels to the error stream
 * 
 * @author Jeremy Unruh
 */
public class ConsoleLogger implements Logger {

    private final String category;
    
    ConsoleLogger(String category) {
        this.category = category;
    }
    
    @Override
    public boolean isErrorEnabled() {
        return true;
    }

    @Override
    public boolean isWarnEnabled() {
        return true;
    }

    @Override
    public boolean isInfoEnabled() {
        return false;
    }

    @Override
    public boolean isDebugEnabled() {
        return false;
    }

    @Override
    public boolean isTraceEnabled() {
        return false;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public void trace(String message, Object... args) {
    }

    @Override
    public void debug(String message, Object... args) {
    }

    @Override
    public void info(String message, Object... args) {
    }

    @Override
    public void warn(String message, Object... args) {
        System.err.printf(String.format("[%s] WARN: %s%n", category, message), args);
    }

    @Override
    public void warn(Throwable throwable, String message, Object... args) {
        System.err.printf(String.format("[%s] WARN: %s%n%s", category, message, Throwables.getStackTraceAsString(throwable)), args);
    }

    @Override
    public void error(String message, Object... args) {
        System.err.printf(String.format("[%s] ERROR: %s%n", category, message), args);
    }

    @Override
    public void error(Throwable throwable, String message, Object... args) {
        System.err.printf(String.format("[%s] ERROR: %s%n%s", category, message, Throwables.getStackTraceAsString(throwable)), args);

    }


}
