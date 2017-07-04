/*******************************************************************************
 * 	Copyright 2017 HuaWei Tld                                     
 * 	                                                                                 
 * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not      
 * 	use this file except in compliance with the License. You may obtain a copy of    
 * 	the License at                                                                   
 * 	                                                                                 
 * 	    http://www.apache.org/licenses/LICENSE-2.0                                   
 * 	                                                                                 
 * 	Unless required by applicable law or agreed to in writing, software              
 * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT        
 * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the         
 * 	License for the specific language governing permissions and limitations under    
 * 	the License.                                                                     
 *******************************************************************************/
package org.openstack4j.openstack.loadbalance.internal;

import static com.google.common.base.Preconditions.*;

import org.openstack4j.api.loadbalance.ELBCertificateService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.loadbalance.Certificate;
import org.openstack4j.model.loadbalance.CertificateUpdate;
import org.openstack4j.openstack.loadbalance.domain.ELBCertificate;
import org.openstack4j.openstack.loadbalance.domain.ELBCertificate.Certificates;

import com.google.common.base.Strings;

public class ELBCertificateSeviceImpl extends BaseELBServices implements ELBCertificateService {

	private static final String API_PATH = "/elbaas/certificate";

	@Override
	public Certificate create(Certificate cert) {
		checkArgument(cert != null, "cert is required");
		checkArgument(!Strings.isNullOrEmpty(cert.getCertificate()), "certificate is required");
		checkArgument(!Strings.isNullOrEmpty(cert.getPrivateKey()), "privateKey is required");

		return post(ELBCertificate.class, uri(API_PATH)).entity(cert).execute();
	}

	@Override
	public ActionResponse delete(String certificateId) {
		checkArgument(!Strings.isNullOrEmpty(certificateId), "certificateId is required");
		return deleteWithResponse(uri("%s/%s", API_PATH, certificateId)).execute();
	}

	@Override
	public Certificate update(String certificateId, CertificateUpdate cert) {
		checkArgument(!Strings.isNullOrEmpty(certificateId), "certificateId is required");
		checkArgument(cert != null, "cert is required");

		return put(ELBCertificate.class, uri("%s/%s", API_PATH, certificateId)).entity(cert).execute();
	}

	@Override
	public Certificates list() {
		return get(Certificates.class, uri(API_PATH)).execute();
	}

}
