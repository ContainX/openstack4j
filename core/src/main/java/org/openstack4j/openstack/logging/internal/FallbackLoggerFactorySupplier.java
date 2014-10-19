package org.openstack4j.openstack.logging.internal;

import org.openstack4j.openstack.logging.Logger;
import org.openstack4j.openstack.logging.LoggerFactorySupplier;

/**
 * Fallback Logger Factory Supplier which returns a Console logger by default only logging WARN and ERROR levels
 * 
 * @author Jeremy Unruh
 *
 */
public final class FallbackLoggerFactorySupplier implements LoggerFactorySupplier {

    private static final FallbackLoggerFactorySupplier INSTANCE = new FallbackLoggerFactorySupplier();
    private boolean useJDKLogger;
    
    private FallbackLoggerFactorySupplier() { 
    }
    
    public static FallbackLoggerFactorySupplier getInstance() {
        return INSTANCE;
    }
    
    public void useJDKLogger() {
        this.useJDKLogger = true;
    }
    
    @Override
    public Logger getLogger(String category) {
        if (useJDKLogger)
            return new JDKLogger(category);
        
        return new ConsoleLogger(category);
    }

    @Override
    public Logger getLogger(Class<?> category) {
        return getLogger(category.getName());
    }

}
