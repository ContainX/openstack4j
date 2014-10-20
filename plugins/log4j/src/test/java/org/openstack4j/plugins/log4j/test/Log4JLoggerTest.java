package org.openstack4j.plugins.log4j.test;

import static org.testng.Assert.assertEquals;

import org.openstack4j.openstack.logging.Logger;
import org.openstack4j.openstack.logging.LoggerFactory;
import org.openstack4j.plugins.log4j.Log4jLogger;
import org.testng.annotations.Test;

public class Log4JLoggerTest {

    public static class LogClass {
        
        private static final Logger LOG = LoggerFactory.getLogger(LogClass.class);
    }
    
    /**
     * Validates OpenStack4j core has picked up this plugin and has associated the default Logger to this Log4j
     */
    @Test
    public void testLogger() {
        assertEquals(LogClass.LOG.getClass(), Log4jLogger.class);
        assertEquals(LogClass.LOG.getCategory(), LogClass.class.getName());
    }
    
}
