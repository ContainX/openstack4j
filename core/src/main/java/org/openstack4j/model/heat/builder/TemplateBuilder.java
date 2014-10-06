package org.openstack4j.model.heat.builder;

import org.openstack4j.common.Buildable;
import org.openstack4j.model.heat.Template;


/**
 * This interface describes a builder for {@link Template} objects 
 * @author Matthias Reisser
 *
 */
public interface TemplateBuilder extends Buildable.Builder<TemplateBuilder, Template>{
	
	/**
	 * sets the template in Json format
	 * @param template template in Json format
	 * @return modified TemplateBuilder
	 */
	TemplateBuilder templateJson(String template);


}
