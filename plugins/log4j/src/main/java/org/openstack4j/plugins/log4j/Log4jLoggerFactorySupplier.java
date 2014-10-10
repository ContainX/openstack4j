package org.openstack4j.plugins.log4j;

import org.openstack4j.openstack.logging.Logger;
import org.openstack4j.openstack.logging.LoggerFactorySupplier;

/**
 * Log4j Logger Factory Supplier
 * 
 * @author Jeremy Unruh
 */
public class Log4jLoggerFactorySupplier implements LoggerFactorySupplier {

    /**
     * {@inheritDoc}
     */
    @Override
    public Logger getLogger(String category) {
        return new Log4jLogger(category, org.apache.log4j.Logger.getLogger(category));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Logger getLogger(Class<?> category) {
        return getLogger(category.getName());
    }

}
