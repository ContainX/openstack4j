/*******************************************************************************
 * 	Copyright 2016 ContainX and OpenStack4j                                          
 * 	                                                                                 
 * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not      
 * 	use this file except in compliance with the License. You may obtain a copy of    
 * 	the License at                                                                   
 * 	                                                                                 
 * 	    http://www.apache.org/licenses/LICENSE-2.0                                   
 * 	                                                                                 
 * 	Unless required by applicable law or agreed to in writing, software              
 * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT        
 * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the         
 * 	License for the specific language governing permissions and limitations under    
 * 	the License.                                                                     
 *******************************************************************************/

package com.huawei.openstack4j.api;

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
     * @see com.huawei.openstack4j.openstack4j.core.transport.internal.HttpExecutor#getExecutorName
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
