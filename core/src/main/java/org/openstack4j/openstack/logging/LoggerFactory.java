package org.openstack4j.openstack.logging;

import java.util.Iterator;
import java.util.ServiceLoader;

import org.openstack4j.openstack.logging.internal.FallbackLoggerFactorySupplier;

/**
 * Responsible for returning a Logger based on a Category.  By default we will look for a logging extension in the classpath and delegate
 * to the extension.  If no logging extensions are found then the default internal fallback Logger
 * 
 * @author Jeremy Unruh
 */
public class LoggerFactory {

    public static Logger getLogger(String category) {
        return getSupplier().getLogger(category);
    }

    public static Logger getLogger(Class<?> category) {
        return getSupplier().getLogger(category);
    }

    private static LoggerFactorySupplier getSupplier() {
        Iterator<LoggerFactorySupplier> it = ServiceLoader.load(LoggerFactorySupplier.class).iterator();
        if (it != null && it.hasNext())
            return it.next();
        
        return FallbackLoggerFactorySupplier.getInstance();
    }
    
}
