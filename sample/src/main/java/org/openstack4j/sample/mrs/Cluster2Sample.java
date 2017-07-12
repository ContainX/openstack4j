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

import org.openstack4j.openstack.sahara.domain.SaharaCluster2;
import org.openstack4j.sample.AbstractSample;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author QianBiao.NG
 * @date   2017-07-05 15:36:39
 */
@Test
public class Cluster2Sample extends AbstractSample {

	public void testGetCluster() {
		SaharaCluster2 cluster = osclient.sahara().clusters2().get("0f4ab6b7-a723-4b6c-b326-f8a5711d365a");
		Assert.assertEquals(cluster.getId(), "0f4ab6b7-a723-4b6c-b326-f8a5711d365a");
	}

	public void testCreateClusterAndRunJob() {
//		osclient.sahara().clusters2().createAndRunJob("");
	}

}
