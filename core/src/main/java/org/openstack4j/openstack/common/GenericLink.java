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
package org.openstack4j.openstack.common;

import org.openstack4j.model.common.Link;
import org.openstack4j.model.common.builder.LinkBuilder;

import com.google.common.base.MoreObjects;

/**
 * A Link holds information about a URL, Relative URL and the type of the link
 *
 * @author Jeremy Unruh
 */
public class GenericLink implements Link {

	private static final long serialVersionUID = 1L;

	private String rel;
	private String href;
	private String type;

	public GenericLink() { }

	public GenericLink(String rel, String href, String type) {
		this.rel = rel;
		this.type = type;
		this.href = href;
	}

	/**
	 * @return the link builder
	 */
	public static LinkBuilder builder() {
		return new LinkConcreteBuilder();
	}

	@Override
	public LinkBuilder toBuilder() {
		return new LinkConcreteBuilder(this);
	}

	/**
	 * @return the relative URL or null
	 */
	public String getRel() {
		return rel;
	}

	/**
	 * @return the href URL
	 */
	public String getHref() {
		return href;
	}

	/**
	 * @return the type of link or null
	 */
	public String getType() {
		return type;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).omitNullValues()
						.add("href", href).add("rel", rel).add("type", type)
						.toString();
	}

	public static class LinkConcreteBuilder implements LinkBuilder {

		GenericLink model;

		LinkConcreteBuilder() {
			this(new GenericLink());
		}

		LinkConcreteBuilder(GenericLink link) {
			this.model = link;
		}

		/**
		 * @see GenericLink#getRel()
		 */
		public LinkConcreteBuilder rel(String rel) {
			model.rel = rel;
			return this;
		}

		/**
		 * @see GenericLink#getHref()
		 */
		public LinkConcreteBuilder href(String href) {
			model.href = href;
			return this;
		}

		/**
		 * @see GenericLink#getType()
		 */
		public LinkConcreteBuilder type(String type) {
			model.type = type;
			return this;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Link build() {
			return model;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public LinkBuilder from(Link in) {
			this.model = (GenericLink)in;
			return this;
		}
	}

}
