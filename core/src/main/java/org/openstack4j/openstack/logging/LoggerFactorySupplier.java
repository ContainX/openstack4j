package org.openstack4j.openstack.logging;

/**
 * Produces instances of {@link Logger} associated with the specified category
 */
public interface LoggerFactorySupplier {
    
    Logger getLogger(String category);
    
    Logger getLogger(Class<?> category);
    
}
