/*******************************************************************************
 * 	Copyright 2017 HuaWei Tld                                     
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
package org.openstack4j.sample.mrs;

import java.util.List;

import org.openstack4j.model.sahara.options.JobExeListOptions;
import org.openstack4j.openstack.sahara.domain.SaharaJobExe;
import org.openstack4j.openstack.sahara.domain.SaharaJobExe.JobState;
import org.openstack4j.sample.AbstractSample;
import org.testng.annotations.Test;

/**
 *
 * @author QianBiao.NG
 * @date   2017-07-05 15:36:39
 */
public class JobExeSample extends AbstractSample {

	@Test
	public void testGetJobExe() {
		SaharaJobExe execution = osclient.sahara().jobExes().get("job-exe-id");
	}

	public void testListJobExe() {
		JobExeListOptions options = JobExeListOptions.create().page(1).pageSize(20).clusterId("cluster-id")
				.state(JobState.Completed);
		List<? extends SaharaJobExe> list = osclient.sahara().jobExes().list(options);
	}

}
