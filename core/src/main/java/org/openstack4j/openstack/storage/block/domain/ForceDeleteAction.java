package org.openstack4j.openstack.storage.block.domain;

import org.openstack4j.model.ModelEntity;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("os-force_delete")
public class ForceDeleteAction implements ModelEntity {

	private static final long serialVersionUID = 1L;

	public ForceDeleteAction() { }
}
