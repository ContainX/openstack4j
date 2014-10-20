package org.openstack4j.openstack.common;

import java.util.Collections;
import java.util.List;

import org.openstack4j.core.transport.ListType;
import org.openstack4j.model.ModelEntity;

/**
 * A List result which wrappers a JSON Array
 *
 * @param <T> the generic type
 */
public abstract class ListResult<T> implements ModelEntity, ListType {

	private static final long serialVersionUID = 1L;

	protected abstract List<T> value();
	
	public List<T> getList() {
		if (value() == null)
			return Collections.emptyList();
		return value();
	}

}
