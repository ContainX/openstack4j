package org.openstack4j.openstack.networking.domain;

import org.openstack4j.model.network.Pool;

import com.google.common.base.Objects;

/**
 * An IP Address Pool which has a starting network and a ending network which becomes a pool of addresses
 * 
 * @author Jeremy Unruh
 */
public class NeutronPool implements Pool {

	private static final long serialVersionUID = 1L;
	
	private String start;
	private String end;
	
	public NeutronPool() { }

	public NeutronPool(String start, String end) {
		this.start = start;
		this.end = end;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getStart() {
		return start;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getEnd() {
		return end;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return Objects.toStringHelper(this).omitNullValues().add("start", start).add("end", end).toString();
	}
}


