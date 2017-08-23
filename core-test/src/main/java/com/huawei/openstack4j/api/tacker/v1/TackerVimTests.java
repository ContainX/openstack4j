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
package com.huawei.openstack4j.api.tacker.v1;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.base.Preconditions;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.api.Builders;
import com.huawei.openstack4j.api.exceptions.ServerResponseException;
import com.huawei.openstack4j.model.common.ActionResponse;
import com.huawei.openstack4j.model.tacker.Vim;
import com.huawei.openstack4j.openstack.tacker.domain.AuthCredentials;
import com.huawei.openstack4j.openstack.tacker.domain.VimProject;

/**
 *
 * @author Vishvesh Deshmukh
 * @date Sep 14, 2016
 */
@Test(suiteName = "tacker/vnim")
public class TackerVimTests extends AbstractTest {

	private static final String TACKER_VIM = "/tacker/v1/vim.json";
	private static final String TACKER_VIMS = "/tacker/v1/vims.json";

	@Override
	protected Service service() {
		return Service.TACKER;
	}

	@Test
	public void testListVims() throws Exception {
		respondWith(TACKER_VIMS);
		List<? extends Vim> vims = osv3().tacker().vim().list();
		assertEquals(1, vims.size());
		Preconditions.checkNotNull(vims.get(0));
		Logger.getLogger(getClass().getName())
				.info(getClass().getName() + " : Tacker VIM from List : " + vims.get(0));
		assertEquals(vims.get(0).getName(), "test-vim");
	}
	
	public void testGetVim() throws IOException {
	    respondWith(TACKER_VIM);
		String id = "bad2f397-7436-4fc7-8043-726e173c5d30";
		Vim vim = osv3().tacker().vim().show(id);
		Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Tacker VIM by ID : "+vim);
		assertNotNull(vim);
		assertEquals(id, vim.getId());
		assertEquals("test-vim", vim.getName());
	}
	
	public void testRegisterVim() throws IOException {
		respondWith(TACKER_VIM);
		VimProject vimProject = VimProject.create().name("admin").projectDomainName("default");
		
		AuthCredentials authCredentials = AuthCredentials.create()
				.username("admin")
				.password("password")
				.userDomainName("default");
		
		Vim vim = Builders.tacker().vim()
				.name("test-vim")
				.description("test-vim-description")
				.authUrl("http://openstack.os4j.com:35357/v3")
				.isDefault(Boolean.TRUE)
				.type("openstack")
				.vimProject(vimProject)
				.authCredentials(authCredentials)
				.build();
		
		vim = osv3().tacker().vim().register(vim);
		Logger.getLogger(getClass().getName()).info(getClass().getName() + " : Created Tacker Vim : "+vim);
		
		assertEquals("test-vim", vim.getName());
	}
	
	@Test(expectedExceptions = ServerResponseException.class)
	public void testRegisterVimWithTackerError() throws IOException {
		String jsonResponse = "{\"TackerError\": {"
                + "\"message\": \"'project_domain_name' is missing.\", "
                + "\"code\": 500}}";
		
		respondWith(500, jsonResponse);
		
		VimProject vimProject = VimProject.create().name("admin");
		
		AuthCredentials authCredentials = AuthCredentials.create()
				.username("admin")
				.password("password")
				.userDomainName("default");
		
		Vim vim = Builders.tacker().vim()
				.name("test-vim")
				.description("test-vim-description")
				.authUrl("http://openstack.os4j.com:35357/v3")
				.isDefault(Boolean.TRUE)
				.type("openstack")
				.vimProject(vimProject)
				.authCredentials(authCredentials)
				.build();
		
		vim = osv3().tacker().vim().register(vim);
		System.out.println("THROWING EXCEPTIONNNNNNNNN");
		Assert.fail("Exception should have been thrown.");
	}
	
	public void testDeleteVim() throws IOException {
		respondWith(200);
		ActionResponse result = osv3().tacker().vim().delete("bad2f397-7436-4fc7-8043-726e173c5d30");
		assertTrue(result.isSuccess());
	}
}