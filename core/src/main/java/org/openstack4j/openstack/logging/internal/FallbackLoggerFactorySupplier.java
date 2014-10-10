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
    
    private FallbackLoggerFactorySupplier() { 
    }
    
    public static FallbackLoggerFactorySupplier getInstance() {
        return INSTANCE;
    }
    
    
    @Override
    public Logger getLogger(String category) {
        return new ConsoleLogger(category);
    }

    @Override
    public Logger getLogger(Class<?> category) {
        return getLogger(category.getName());
    }

}
