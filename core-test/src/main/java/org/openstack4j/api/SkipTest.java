
package org.openstack4j.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation allowing TestNG to skip test for a specific connector
 * 
 * @author Bruno Semperlotti
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SkipTest {
    
    /**
     * A regex identifying the targeted HTTP connector name
     * 
     * <p>Examples:
     * <code>
     *      "Jersey 2 Connector", ".*"
     * </code>
     * </p>
     * 
     * @see org.openstack4j.core.transport.internal.HttpExecutor#getExecutorName
     */
    public String connector();
    
    /**
     * The GitHub issue identifier (optional)
     */
    public int issue() default -1;
    
    /**
     * A message describing the reason of test skip (optional)
     */
    public String description() default "";
}
