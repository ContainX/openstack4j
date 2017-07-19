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
package org.openstack4j.sample.message.notification;

import java.util.List;

import org.openstack4j.openstack.message.notification.constant.Protocol;
import org.openstack4j.openstack.message.notification.domain.MessageTemplate;
import org.openstack4j.openstack.message.notification.domain.MessageTemplateCreate;
import org.openstack4j.openstack.message.notification.domain.TracableRequest;
import org.openstack4j.openstack.message.notification.options.MessageTemplateListOptions;
import org.openstack4j.sample.AbstractSample;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.util.Strings;

@Test(suiteName = "SimpleMessageNotificatoin/MessageTemplate/Sample")
public class MessageTemplateSample extends AbstractSample {

	String name = randomName();
	MessageTemplate template = null;

	/**
	 * prepare resources
	 */
	@BeforeClass
	public void prepare() {
		MessageTemplateCreate create = MessageTemplateCreate.builder().name(name).protocol(Protocol.EMAIL)
				.locale("zh-cn").content(name).build();
		template = osclient.notification().templates().create(create);
	}

	/**
	 * clean up resources
	 */
	@AfterClass
	public void cleanup() {
		TracableRequest delete = osclient.notification().templates().delete(template.getId());
		Assert.assertTrue(!Strings.isNullOrEmpty(delete.getRequestId()));
	}
	
	@Test(priority = 0)
	public void testUpdateTemplate() {
		TracableRequest update = osclient.notification().templates().updateContent(template.getId(), name + "-updated");
		Assert.assertTrue(!Strings.isNullOrEmpty(update.getRequestId()));
	}

	@Test(priority = 1)
	public void testListTemplates() {
		MessageTemplateListOptions options = MessageTemplateListOptions.create().limit(1).offset(0).name(name)
				.protocol(Protocol.EMAIL);
		List<? extends MessageTemplate> templates = osclient.notification().templates().list(options);

		MessageTemplate messageTemplate = templates.get(0);
		Assert.assertNotNull(messageTemplate);
		Assert.assertEquals(messageTemplate.getName(), name);
		// Assert.assertEquals(messageTemplate.getLocale(), "zh-cn");
		Assert.assertEquals(messageTemplate.getId(), template.getId());
	}

	@Test(priority = 1)
	public void testGetTemplate() {
		MessageTemplate get = osclient.notification().templates().get(template.getId());
		Assert.assertNotNull(get);
		Assert.assertEquals(get.getId(), get.getId());
		Assert.assertEquals(get.getName(), name);
		// Assert.assertEquals(get.getLocale(), "zh-cn");
		Assert.assertEquals(get.getProtocol(), Protocol.EMAIL);
		Assert.assertEquals(get.getContent(), name + "-updated");
		Assert.assertNotNull(get.getCreateTime());
		Assert.assertNotNull(get.getUpdateTime());
	}


}
