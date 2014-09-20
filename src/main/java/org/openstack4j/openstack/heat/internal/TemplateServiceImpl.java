package org.openstack4j.openstack.heat.internal;

import org.openstack4j.api.Builders;
import org.openstack4j.api.heat.TemplateService;
import org.openstack4j.model.heat.Template;
import org.openstack4j.openstack.heat.domain.HeatTemplate;

/**
 * This class implements all methods for manipulation of {@link HeatTemplate}
 * objects. The non-exhaustive list of methods is oriented along
 * http://developer.openstack.org/api-ref-orchestration-v1.html#stack-templates
 * 
 * 
 * @author Matthias Reisser
 * 
 */
public class TemplateServiceImpl extends BaseHeatServices implements
		TemplateService {

	@Override
	public String validateTemplate(String template) {
		return validateTemplate(Builders.template().templateJson(template)
				.build());
	}

	@Override
	public String validateTemplate(Template template) {
		String result = "VALID";

		try {
			post(String.class, uri("/validate")).entity(template).execute();
		} catch (RuntimeException re) {
			result = re.getMessage();
		}
		return result;
	}

}
