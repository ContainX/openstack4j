package org.openstack4j.api.heat;

import org.openstack4j.model.heat.Template;

/**
 * This Interface contains a non-exhaustive list of methods for the manipulation of Heat Templates
 * @author Matthias Reisser
 *
 */
public interface TemplateService {

	/**
	 * Validates the template
	 * @param template to validate, passed as a {@link Template}
	 * @return String containing details about the validity of the template. Returns "VALID" if the template is valid.
	 */
	String validateTemplate(Template template);
	
	/**
	 * Validates the template
	 * @param template to validate, passed as {@link String} in JSON Format
	 * @return String containing details about the validity of the template. Returns "VALID" if the template is valid.
	 */
	String validateTemplate(String template);
	
}
