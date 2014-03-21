package org.openstack4j.openstack.identity.domain;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.openstack4j.model.identity.Service;
import org.openstack4j.model.identity.builder.ServiceBuilder;
import org.openstack4j.openstack.common.ListResult;

import com.google.common.base.Objects;

public class KeystoneService implements Service {

	private static final long serialVersionUID = 1L;
	
	String id;
	String type;
	String name;
	String description;
	
	public static ServiceBuilder builder() {
		return new ServiceConcreteBuilder();
	}
	
	public String getId() {
		return id;
	}
	
	public String getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return Objects.toStringHelper(this).omitNullValues()
				   .add("id", id).add("name", name).add("type", type).add("description", description)
				   .toString();
	}
	
	public static class Services extends ListResult<KeystoneService> {

		private static final long serialVersionUID = 1L;
		@JsonProperty("OS-KSADM:services")
		private List<KeystoneService> list;
		
		public List<KeystoneService> value() {
			return list;
		}
	}
	
	public static class ServiceConcreteBuilder implements ServiceBuilder {

		private KeystoneService model = new KeystoneService();
		
		public ServiceBuilder name(String name) {
			model.name = name;
			return this;
		}
		
		public ServiceBuilder type(String type) {
			model.type = type;
			return this;
		}
		
		public ServiceBuilder description(String description) {
			model.description = description;
			return this;
		}
		
		@Override
		public Service build() {
			return model;
		}

		@Override
		public ServiceBuilder from(Service in) {
			model = (KeystoneService)in;
			return this;
		}
		
	}
	
}
