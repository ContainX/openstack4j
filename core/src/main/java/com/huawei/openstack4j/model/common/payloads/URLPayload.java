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
package com.huawei.openstack4j.model.common.payloads;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import com.google.common.base.Throwables;

import com.huawei.openstack4j.model.common.Payload;

/**
 * URL based Payload
 * 
 * @author Jeremy Unruh
 */
public class URLPayload implements Payload<URL> {

	URL url;
	InputStream is;
	
	public URLPayload(URL url) {
		this.url = url;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() throws IOException {
		if (is != null)
			is.close();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InputStream open() {
		try {
			is = url.openStream();
			return is;
		}
		catch (IOException e) {
			throw Throwables.propagate(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void closeQuietly() {
		try
		{
			if (is != null)
				is.close();
		}
		catch (IOException e) { }
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public URL getRaw() {
		return url;
	}

}
