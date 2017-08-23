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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.google.common.base.Throwables;

import com.huawei.openstack4j.model.common.Payload;

/**
 * A File based Payload
 * 
 * @author Jeremy Unruh
 */
public class FilePayload implements Payload<File> {

	private File file;
	private InputStream is;
	
	public FilePayload(File file) {
		this.file = file;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public InputStream open() {
		try {
			is = new FileInputStream(file);
			return is;
		}
		catch (FileNotFoundException e) {
		  throw Throwables.propagate(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public File getRaw() {
		return file;
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
	public void closeQuietly() {
		try
		{
			close();
		}
		catch (IOException e) { }
	}

}
