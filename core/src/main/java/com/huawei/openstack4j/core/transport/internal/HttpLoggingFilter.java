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
package com.huawei.openstack4j.core.transport.internal;


/**
 * Handles turning Http Wire logging on/off for supported connectors.  Some connectors need have specific registration and use this class
 * to determine if wire logging is enabled
 * 
 * @author Jeremy Unruh
 */
public final class HttpLoggingFilter {

    private HttpLoggingFilter() { }
    
    public static void toggleLogging(boolean isEnabled) {
        System.getProperties().setProperty(HttpLoggingFilter.class.getName(), String.valueOf(isEnabled));
        System.setProperty("org.apache.commons.logging.Log","org.apache.commons.logging.impl.SimpleLog");
        System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http", isEnabled ? "DEBUG" : "WARN");
        System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire", "ERROR");
    }
    
    public static boolean isLoggingEnabled() {
        return Boolean.getBoolean(HttpLoggingFilter.class.getName());
    }
}
