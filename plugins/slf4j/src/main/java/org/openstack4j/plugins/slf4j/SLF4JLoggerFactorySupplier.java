package org.openstack4j.plugins.slf4j;

import org.openstack4j.openstack.logging.Logger;
import org.openstack4j.openstack.logging.LoggerFactorySupplier;

/**
 * SLF4J Logger Factory Supplier
 * 
 * @author Jeremy Unruh
 */
public class SLF4JLoggerFactorySupplier implements LoggerFactorySupplier {

    /**
     * {@inheritDoc}
     */
    @Override
    public Logger getLogger(String category) {
        return new SLF4JLogger(category, org.slf4j.LoggerFactory.getLogger(category));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Logger getLogger(Class<?> category) {
        return getLogger(category.getName());
    }

}
