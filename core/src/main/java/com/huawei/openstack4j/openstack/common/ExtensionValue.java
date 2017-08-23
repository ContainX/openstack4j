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
package com.huawei.openstack4j.openstack.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.common.Extension;
import com.huawei.openstack4j.model.common.Link;

import com.google.common.base.MoreObjects;

import java.net.URI;
import java.util.Date;
import java.util.List;

/**
 * Represents an Extension which adds additional functionality to the OpenStack API
 *
 * @author Jeremy Unruh
 */
public class ExtensionValue implements Extension {

	private static final long serialVersionUID = 1L;
	String name;
	URI namespace;
	String alias;
	Date updated;
	String description;
	List<GenericLink> links;

	/**
	 * {@inheritDoc}
	 */
	public String getName() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	public URI getNamespace() {
		return namespace;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * {@inheritDoc}
	 */
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ssX")
	public Date getUpdated() {
		return updated;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<? extends Link> getLinks() {
		return links;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(Extension.class).omitNullValues()
						.add("name", name)
						.add("namespace", namespace)
						.add("description", description)
						.add("alias", alias)
						.add("updated", updated)
						.add("links", links)
						.addValue("\n")
						.toString();
	}

	@JsonRootName("extensions")
	public static class ExtensionList extends ListResult<ExtensionValue> {
		private static final long serialVersionUID = 1L;

		@JsonProperty("values")
		private List<ExtensionValue> list;

		public List<ExtensionValue> value() {
			return list;
		}
	}

	public static class Extensions extends ListResult<ExtensionValue> {
		private static final long serialVersionUID = 1L;

		@JsonProperty("extensions")
		private List<ExtensionValue> list;

		public List<ExtensionValue> value() {
			return list;
		}
	}
}
