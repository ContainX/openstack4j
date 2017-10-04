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
package com.huawei.openstack4j.functional.message.notification;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.util.Strings;

import com.google.common.collect.Lists;

import com.huawei.openstack4j.functional.AbstractTest;
import com.huawei.openstack4j.openstack.message.notification.constant.Protocol;
import com.huawei.openstack4j.openstack.message.notification.domain.MessageTemplate;
import com.huawei.openstack4j.openstack.message.notification.domain.MessageTemplateCreate;
import com.huawei.openstack4j.openstack.message.notification.domain.TracableRequest;
import com.huawei.openstack4j.openstack.message.notification.options.MessageTemplateListOptions;

@Test(suiteName = "SimpleMessageNotificatoin/MessageTemplate/Test")
public class MessageTemplateTest extends AbstractTest {

	String name = randomName();
	MessageTemplate template = null;

	/**
	 * prepare resources
	 */
	@BeforeClass
	public void prepare() {
		MessageTemplateCreate create = MessageTemplateCreate.builder().name(name).protocol(Protocol.EMAIL)
				.locale("zh-cn").content(name).build();
		template = osclient.notification().messageTemplates().create(create);
	}

	/**
	 * clean up resources
	 */
	@AfterClass
	public void cleanup() {
		TracableRequest delete = osclient.notification().messageTemplates().delete(template.getId());
		Assert.assertTrue(!Strings.isNullOrEmpty(delete.getRequestId()));
	}

	@Test(priority = 0)
	public void testUpdateTemplate() {
		TracableRequest update = osclient.notification().messageTemplates().updateContent(template.getId(),
				"sdk-unittest, {tag1}");
		Assert.assertTrue(!Strings.isNullOrEmpty(update.getRequestId()));
	}

	@Test(priority = 1)
	public void testListTemplates() {
		MessageTemplateListOptions options = MessageTemplateListOptions.create().limit(1).offset(0).name(name)
				.protocol(Protocol.EMAIL);
		List<? extends MessageTemplate> templates = osclient.notification().messageTemplates().list(options);

		MessageTemplate messageTemplate = templates.get(0);
		Assert.assertNotNull(messageTemplate);
		Assert.assertEquals(messageTemplate.getName(), name);
		// Assert.assertEquals(messageTemplate.getLocale(), "zh-cn");
		Assert.assertEquals(messageTemplate.getId(), template.getId());
	}

	@Test(priority = 1)
	public void testGetTemplate() {
		MessageTemplate get = osclient.notification().messageTemplates().get(template.getId());
		Assert.assertNotNull(get);
		Assert.assertEquals(get.getId(), get.getId());
		Assert.assertEquals(get.getName(), name);
		// Assert.assertEquals(get.getLocale(), "zh-cn");
		Assert.assertEquals(get.getProtocol(), Protocol.EMAIL);
		Assert.assertEquals(get.getContent(), "sdk-unittest, {tag1}");
		Assert.assertEquals(get.getTags(), Lists.newArrayList("tag1"));
		Assert.assertNotNull(get.getCreateTime());
		Assert.assertNotNull(get.getUpdateTime());
	}

}
