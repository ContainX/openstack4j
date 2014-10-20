package org.openstack4j.openstack.identity.domain;

import java.util.List;

import org.openstack4j.api.Apis;
import org.openstack4j.model.identity.Tenant;
import org.openstack4j.model.identity.builder.TenantBuilder;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.Objects;

/**
 * Tenant Model class use to group/isolate resources and/or identity objects
 * 
 * @author Jeremy Unruh
 * 
 * @see <a href="http://docs.openstack.org/api/openstack-identity-service/2.0/content/GET_listTenants_v2.0_tenants_Tenant_Operations.html#GET_listTenants_v2.0_tenants_Tenant_Operations-Response"
 */
@JsonRootName("tenant")
@JsonIgnoreProperties(ignoreUnknown=true)
public class KeystoneTenant implements Tenant {

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String description;
	private Boolean enabled = true;
	
	/**
	 * @return the Tenant builder
	 */
	public static TenantBuilder builder() {
		return new TenantConcreteBuilder();
	}
	
	@Override
	public TenantBuilder toBuilder() {
		return new TenantConcreteBuilder(this);
	}
	
	/**
	 * By providing an ID it is assumed this object will be mapped to an existing Tenant
	 *
	 * @return the id of the tenant
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @return the name of the tenant
	 */
	public String getName() {
		return name;
	}
	
	
	/**
	 * @return the description of the tenant
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEnabled() {
		return (enabled != null && enabled);
	}
	
	/**
	 * Sets the enabled.
	 *
	 * @param enabled the new enabled
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete() {
		if (id != null)
			Apis.getIdentityServices().tenants().delete(getId());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update() {
		if (id != null)
			Apis.getIdentityServices().tenants().update(this);
		}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addUser(String userId, String roleId) {
		Apis.getIdentityServices().roles().addUserRole(id, userId, roleId);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeUser(String userId, String roleId) {
		Apis.getIdentityServices().roles().removeUserRole(id, userId, roleId);
	}
	
	 /**
 	 * {@inheritDoc}
 	 */
 	@Override
   public int hashCode() {
      return Objects.hashCode(id, name, description);
   }
	 
	 /**
 	 * {@inheritDoc}
 	 */
 	@Override
   public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null || getClass() != obj.getClass()) return false;
      KeystoneTenant that = KeystoneTenant.class.cast(obj);
      return Objects.equal(this.id, that.id)
            && Objects.equal(this.name, that.name)
            && Objects.equal(this.description, that.description);
   }
	
	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return Objects.toStringHelper(this).omitNullValues()
				   .add("id", id).add("name", name).add("description", description)
				   .add("enabled", enabled)
				   .toString();
	}
	
	public static class TenantConcreteBuilder implements TenantBuilder {

		KeystoneTenant model;
		
		TenantConcreteBuilder() {
			this(new KeystoneTenant());
		}
		
		TenantConcreteBuilder(KeystoneTenant model) {
			this.model = model;
		}
		
		/**
		 * @see KeystoneTenant#getName()
		 */
		public TenantBuilder name(String name) {
			model.name = name;
			return this;
		}
		
		/**
		 * @see KeystoneTenant#getDescription()
		 */
		public TenantBuilder description(String desc) {
			model.description = desc;
			return this;
		}
		
		/**
		 * @see KeystoneTenant#getId()
		 */
		public TenantBuilder id(String id) {
			model.id = id;
			return this;
		}
		
		/**
		 * @see KeystoneTenant#getEnabled()
		 */
		public TenantBuilder enabled(boolean enabled) {
			model.enabled = enabled;
			return this;
		}
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public Tenant build() {
			return model;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public TenantBuilder from(Tenant in) {
			if (in != null)
				this.model = (KeystoneTenant)in;
			return this;
		}
	}
	
	public static class Tenants extends ListResult<KeystoneTenant> {

		private static final long serialVersionUID = 1L;
		@JsonProperty("tenants")
		private List<KeystoneTenant> list;
		
		public List<KeystoneTenant> value() {
			return list;
		}
	}
}
