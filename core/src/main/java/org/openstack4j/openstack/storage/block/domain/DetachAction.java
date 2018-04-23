package org.openstack4j.openstack.storage.block.domain;

import org.openstack4j.model.ModelEntity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * os-detach
 * @author capitek-xuning（首信科技-徐宁）
 *
 */
@JsonRootName("os-detach")
public class DetachAction implements ModelEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * The ID of the attachment.
	 */
	@JsonProperty("attachment_id")
	private String attachmentId;

	/**
	 * @author capitek-xuning（首信科技-徐宁）
	 * @param attachmentId The ID of the attachment.
	 */
	public DetachAction(String attachmentId) {
		super();
		this.attachmentId = attachmentId;
	}

	public String getAttachmentId() {
		return attachmentId;
	}

}
