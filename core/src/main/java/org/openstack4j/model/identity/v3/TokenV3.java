package org.openstack4j.model.identity.v3;

import java.util.Date;
import java.util.List;

import org.openstack4j.model.common.BasicResource;
import org.openstack4j.model.identity.Token;

/**
 * A version 3 token which is used during authentication allowing follow up calls to only supply the assigned token within the header avoiding re-authentication
 * 
 * @author Jeremy Unruh
 * @see http://docs.openstack.org/api/openstack-identity-service/2.0/content/POST_admin-authenticate_v2.0_tokens_Token_Operations.html
 */
public interface TokenV3 extends Token {

	Date getIssuedAt();
	
	List<String> getMethods();
	
	ProjectV3 getProject();
	
	List<? extends RoleV3> getRoles();
	
	List<? extends Catalog> getCatalog();
	
	public interface RoleV3 extends BasicResource {
	}
	
	public interface ProjectV3 extends BasicResource {
		  
		 DomainV3 getDomain();
		
		 public interface DomainV3 extends BasicResource {
		 }
		
	}
}
