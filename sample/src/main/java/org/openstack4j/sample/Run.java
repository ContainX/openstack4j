package org.openstack4j.sample;

import java.util.List;

import org.openstack4j.openstack.message.notification.constant.Protocol;
import org.openstack4j.openstack.message.notification.domain.MessageTemplate;
import org.openstack4j.openstack.message.notification.options.MessageTemplateListOptions;
import org.testng.annotations.Test;

/**
 *
 * @author QianBiao.NG
 * @date   2017-07-18 15:01:10
 */
public class Run extends AbstractSample {

	@Test
	public void run() {
		MessageTemplateListOptions options = MessageTemplateListOptions.create().limit(1)
				.name("SDK-8ebc9dea-44ec-4cc1-8cc2-4ac644fc87f2").offset(0).protocol(Protocol.EMAIL);
		List<? extends MessageTemplate> templates = osclient.notification().templates().list(options);
		System.out.println(templates);
	}
	
	@Test
	public void delete() {
		osclient.notification().templates().delete("2b1c9a38020d4a1ab4d7dbfdca7c3246");
	}
}
