package org.openstack4j.openstack.common;

import org.openstack4j.model.common.BasicResource;

import com.google.common.base.Objects;

/**
 * Basic Id/Name based Entity Model implementation
 * 
 * @author Jeremy Unruh
 */
public class BasicResourceEntity implements BasicResource {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(getClass()).omitNullValues()
				     .add("id", id).add("name", name)
				     .toString();
	}
}
