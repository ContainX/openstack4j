package org.openstack4j.openstack.compute.domain;

import java.util.List;

import org.openstack4j.model.compute.FlavorAccess;
import org.openstack4j.model.compute.builder.FlavorAccessBuilder;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.Objects;


@JsonIgnoreProperties(ignoreUnknown=true)
public class NovaFlavorAccess implements FlavorAccess {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("flavor_id")
	private String flavorId;
	
	@JsonProperty("tenant_id")
	private String tenantId;
	
	@JsonProperty("tenant")
	private String tenant;
	
	@Override
	public FlavorAccessBuilder toBuilder() {
		return new FlavorAccessConcreteBuilder();
	}
	
	public FlavorAccessBuilder builder() {
		return new FlavorAccessConcreteBuilder(this);
	}
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getFlavorId() {
		return flavorId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTenantId() {
		return tenantId;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTenant() {
		return tenant;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return Objects.toStringHelper(this).omitNullValues()
			.add("flavorId", flavorId)
			.add("tenantId", tenantId)
			.add("tenant", tenant)
			.addValue("\n")
			.toString();
	}
	
	
	@JsonRootName("addTenantAccess")
	public static class AddTenantAccess extends NovaFlavorAccess {

		private static final long serialVersionUID = 1L;
		
	}
	
	@JsonRootName("removeTenantAccess")
	public static class RemoveTenantAccess extends NovaFlavorAccess {

		private static final long serialVersionUID = 1L;
		
	}
	
	
	
	public static class FlavorAccesses extends ListResult<NovaFlavorAccess> {
		
		private static final long serialVersionUID = 1L;
		
		@JsonProperty("flavor_access")
		List<NovaFlavorAccess> flavorAccesses;
		
		@Override
		protected List<NovaFlavorAccess> value() {
			return flavorAccesses;
		}
		
	}
	
	public static class FlavorAccessConcreteBuilder implements FlavorAccessBuilder {
		
		private NovaFlavorAccess m;
		
		FlavorAccessConcreteBuilder(){
			this(new NovaFlavorAccess());
		}
		
		FlavorAccessConcreteBuilder(NovaFlavorAccess model) {
			this.m = model;
		}

		@Override
		public FlavorAccess build() {
			return m;
		}

		@Override
		public FlavorAccessBuilder from(FlavorAccess in) {
			m = (NovaFlavorAccess)in;
			return this;
		}

		@Override
		public FlavorAccessBuilder flavorId(String flavorId) {
			m.flavorId = flavorId;
			return this;
		}

		@Override
		public FlavorAccessBuilder tenantId(String tenantId) {
			m.tenantId = tenantId;
			return this;
		}

		@Override
		public FlavorAccessBuilder tenant(String tenant) {
			m.tenant = tenant;
			return this;
		}
		
	}

}
