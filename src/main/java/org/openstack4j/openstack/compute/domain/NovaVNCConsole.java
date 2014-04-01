package org.openstack4j.openstack.compute.domain;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.openstack4j.model.compute.VNCConsole;

import com.google.common.base.Objects;

/**
 * Represents a VNC Console which provides VNC connection information for a remote server
 *
 * @author Jeremy Unruh
 */
@JsonRootName("console")
public class NovaVNCConsole implements VNCConsole {

	private static final long serialVersionUID = 1L;
	private static final String JSON_REQ_FORMAT = "{ \"os-getVNCConsole\": { \"type\": \"%s\"} }";
	
	@JsonProperty
	private Type type;
	
	@JsonProperty
	private String url;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Type getType() {
		return type;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getURL() {
		return url;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return Objects.toStringHelper(this).omitNullValues().add("type", type).add("url", url).toString();
	}

	public static String getJSONAction(Type type) {
		return String.format(JSON_REQ_FORMAT, type.value());
	}
}
