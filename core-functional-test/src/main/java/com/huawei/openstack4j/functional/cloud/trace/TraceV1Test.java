/*******************************************************************************
 *  Copyright 2017 Huawei TLD
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
/*******************************************************************************
 *******************************************************************************/
package com.huawei.openstack4j.functional.cloud.trace;

import java.util.List;

import org.testng.annotations.Test;

import com.huawei.openstack4j.functional.AbstractTest;
import com.huawei.openstack4j.openstack.cloud.trace.v1.domain.Trace;
import com.huawei.openstack4j.openstack.cloud.trace.v1.options.TraceListOptions;

@Test(suiteName = "CloudTrace/TraceV1/Test")
public class TraceV1Test extends AbstractTest {


	@Test(priority = 1)
	public void testListTrace() {
		TraceListOptions options = TraceListOptions.create().limit(5).user("zhangdong").serviceType("CTS");
		List<Trace> list = osclient.cloudTraceV1().traces().list("system", options);
		
		Trace trace = list.get(list.size() -1);
		options.marker(trace.getId());
		List<Trace> list2 = osclient.cloudTraceV1().traces().list("system", options);
		
	}


}
