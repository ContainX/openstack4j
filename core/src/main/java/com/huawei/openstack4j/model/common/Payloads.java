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
package com.huawei.openstack4j.model.common;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import com.huawei.openstack4j.model.common.payloads.FilePayload;
import com.huawei.openstack4j.model.common.payloads.InputStreamPayload;
import com.huawei.openstack4j.model.common.payloads.URLPayload;

/**
 * Utility class for creating supported Payloads.
 *
 * @author Jeremy Unruh
 */
public class Payloads {

	/**
	 * Creates a new File based Payload
	 *
	 * @param file the file to send
	 * @return the Payload
	 */
	public static Payload<File> create(File file) {
		return new FilePayload(file);
	}
	
	/**
	 * Creates a new Input Stream based Payload
	 *
	 * @param stream the input stream
	 * @return the Payload
	 */
	public static Payload<InputStream> create(InputStream stream) {
		return new InputStreamPayload(stream);
	}
	
	/**
	 * Creates a new URL based Payload allowing direct upload from the URL
	 *
	 * @param url the URL
	 * @return the Payload
	 */
	public static Payload<URL> create(URL url) {
		return new URLPayload(url);
	}
	
}
