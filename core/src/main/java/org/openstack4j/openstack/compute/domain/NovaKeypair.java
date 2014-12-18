package org.openstack4j.openstack.compute.domain;

import java.util.List;

import org.openstack4j.model.compute.Keypair;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;

/**
 * An OpenStack Keypair is an SSH Key
 * 
 * @author Jeremy Unruh
 */
@JsonRootName("keypair")
public class NovaKeypair implements Keypair {

	private static final long serialVersionUID = 1L;

	private String name;
	@JsonProperty("public_key")
	private String publicKey;
	@JsonProperty("private_key")
	private String privateKey;
	private String fingerprint;
	
	/**
	 * Used internally by the domain side of the API to create a new Keypair on an OpenStack server
	 * 
	 * @param name the name of the keypair
	 * @param publicKey the public key or null to have OS generated one
	 * @return NovaKeypair
	 */
	public static NovaKeypair create(String name, String publicKey) {
		NovaKeypair kp = new NovaKeypair();
		kp.name = name;
	  kp.publicKey = publicKey;
	  return kp;
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
	public String getPublicKey() {
		return publicKey;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    public String getPrivateKey() {
        return privateKey;
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getFingerprint() {
		return fingerprint;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return Objects.toStringHelper(this).omitNullValues()
				     .add("name", name).add("public_key", publicKey).add("private_key",  privateKey).add("fingerprint", fingerprint).toString();
	}
	
	public static class Keypairs extends ListResult<NovaKeypair> {

		private static final long serialVersionUID = 1L;
		
		@JsonProperty("keypairs")
		private List<KeyPairWrapper> wrapped;
		private transient List<NovaKeypair> unwrapped;
		
		@Override
		protected List<NovaKeypair> value() {
			if (wrapped != null && unwrapped == null) {
				unwrapped = Lists.newArrayList();
				for (KeyPairWrapper kp : wrapped)
					unwrapped.add(kp.keypair);
			}
			return unwrapped;
		}
		
		static final class KeyPairWrapper {
			@JsonProperty
			private NovaKeypair keypair;
			
		}
		
	}
}
