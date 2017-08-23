/*******************************************************************************
 *  Copyright 2017 HuaWei and OTC tld
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
package com.huawei.openstack4j.openstack.common.functions;

import java.net.MalformedURLException;
import java.net.URL;

import com.google.common.base.Function;

import com.huawei.openstack4j.api.exceptions.OS4JException;

/**
 */
public class GetRootOfURL implements Function<String, String> {

	public static GetRootOfURL instance() {
		return new GetRootOfURL();
	}

	@Override
	public String apply(String input) {
		try {
			URL url = new URL(input);
			String authority = url.getAuthority();
			return input.substring(0, input.indexOf(authority) + authority.length());
		} catch (MalformedURLException e) {
			throw new OS4JException(String.format("endpoint `%s` is not a valid URL", input), e);
		}
	}
	
	public static void main(String[] args) {
		String apply = GetRootOfURL.instance().apply("http://www.baidu.com:10000/");
		System.out.println(apply);
	}

}
