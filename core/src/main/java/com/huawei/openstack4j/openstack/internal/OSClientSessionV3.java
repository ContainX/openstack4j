/*******************************************************************************
 *  Copyright 2017 Huawei TLD
 * 	Copyright 2016 ContainX and OpenStack4j                                          
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
package com.huawei.openstack4j.openstack.internal;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

import org.slf4j.LoggerFactory;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

import com.huawei.openstack4j.api.Apis;
import com.huawei.openstack4j.api.OSClient.OSClientV3;
import com.huawei.openstack4j.api.client.CloudProvider;
import com.huawei.openstack4j.api.identity.EndpointURLResolver;
import com.huawei.openstack4j.api.loadbalance.ELBService;
import com.huawei.openstack4j.api.scaling.AutoScalingService;
import com.huawei.openstack4j.api.telemetry.TelemetryAodhService;
import com.huawei.openstack4j.api.telemetry.TelemetryService;
import com.huawei.openstack4j.api.types.Facing;
import com.huawei.openstack4j.api.types.ServiceType;
import com.huawei.openstack4j.core.transport.Config;
import com.huawei.openstack4j.model.common.Identifier;
import com.huawei.openstack4j.model.identity.AuthVersion;
import com.huawei.openstack4j.model.identity.URLResolverParams;
import com.huawei.openstack4j.model.identity.v3.Token;
import com.huawei.openstack4j.openstack.identity.v3.domain.KeystoneAuth.AuthScope;
import com.huawei.openstack4j.openstack.antiddos.internal.AntiDDoSServices;
import com.huawei.openstack4j.openstack.cloud.trace.v1.internal.CloudTraceV1Service;
import com.huawei.openstack4j.openstack.cloud.trace.v2.internal.CloudTraceV2Service;
import com.huawei.openstack4j.openstack.common.Auth;
import com.huawei.openstack4j.openstack.database.internal.DatabaseServices;
import com.huawei.openstack4j.openstack.identity.v3.domain.KeystoneAuth;
import com.huawei.openstack4j.openstack.identity.v3.domain.KeystoneProject;
import com.huawei.openstack4j.openstack.identity.v3.domain.KeystoneToken;
import com.huawei.openstack4j.openstack.maas.internal.MaaSService;
import com.huawei.openstack4j.openstack.message.notification.internal.NotificationService;
import com.huawei.openstack4j.openstack.message.queue.internal.MessageQueueService;

/**
 * A client which has been identified. Any calls spawned from this session will
 * automatically utilize the original authentication that was successfully
 * validated and authorized
 *
 * @author Jeremy Unruh
 */

public class OSClientSessionV3 extends OSClientSession<OSClientSessionV3, OSClientV3> implements OSClientV3 {

	public OSClientSessionV3() {

	}

	protected AuthScope scope;

	private String user;

	private String password;

	private Identifier domain;

	private String authUrl;

	private String projectId;

	public static final String HTTPS = "https";

	public static final String SEPARATOR = "/";

	public static final String POINT = ":";

	public OSClientSessionV3(Token token, String endpoint, Facing perspective, CloudProvider provider, Config config) {
		this.token = token;
		this.config = config;
		this.perspective = perspective;
		this.provider = provider;
		sessions.set(this);
	}

	@SuppressWarnings("unused")
	private OSClientSessionV3(Token token, OSClientSessionV3 parent, String region) {
		this.token = parent.token;
		this.perspective = parent.perspective;
		this.region = region;
	}

	public static OSClientSessionV3 createSession(Token token) {
		return new OSClientSessionV3(token, token.getEndpoint(), null, null, null);
	}

	public static OSClientSessionV3 createSession(Token token, Facing perspective, CloudProvider provider,
			Config config)

	{
		return new OSClientSessionV3(token, token.getEndpoint(), perspective, provider, config);
	}

	public String getXOpenstackRequestId() {
		return reqId;
	}

	@Override
	public Token getToken() {
		return token;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getEndpoint() {
		return token.getEndpoint();
	}

	@Override
	public AuthVersion getAuthVersion() {
		return AuthVersion.V3;
	}

	private String addNATIfApplicable(String url) {
		if (config != null && config.isBehindNAT()) {
			try {
				URI uri = new URI(url);
				return url.replace(uri.getHost(), config.getEndpointNATResolution());
			} catch (URISyntaxException e) {
				LoggerFactory.getLogger(OSClientSessionV3.class).error(e.getMessage(), e);
			}
		}
		return url;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getEndpoint(ServiceType service) {
		final EndpointURLResolver eUrlResolver = (config != null && config.getEndpointURLResolver() != null)
				? config.getEndpointURLResolver() : fallbackEndpointUrlResolver;
		return addNATIfApplicable(eUrlResolver.findURLV3(URLResolverParams.create(token, service)
				.resolver(config != null ? config.getResolver() : null).perspective(perspective).region(region)));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTokenId() {
		return token.getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public com.huawei.openstack4j.api.identity.v3.IdentityService identity() {
		return Apis.getIdentityV3Services();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<ServiceType> getSupportedServices() {
		if (supports == null)
			supports = Sets.immutableEnumSet(Iterables.transform(token.getCatalog(),
					new com.huawei.openstack4j.openstack.identity.v3.functions.ServiceToServiceType()));
		return supports;
	}

	@Override
	public TelemetryService telemetry() {
		return Apis.get(TelemetryAodhService.class);
	}

	@Override
	public com.huawei.openstack4j.api.OSClient.OSClientV3 scopeToProject(Identifier project) {
		this.scope = AuthScope.project(project);
		return this;
	}

	public OSClientV3 withConfig(Config config) {
		this.config = config;
//		if (this.endpointUrl != null) {
//			if (null != this.config.getEndpointURLResolver()) {
//				EndpointURLResolver endpointResolver = this.config.getEndpointURLResolver();
//				if (endpointResolver instanceof OverridableEndpointURLResolver) {
//					OverridableEndpointURLResolver endpointResolver1 = (OverridableEndpointURLResolver) endpointResolver;
//					endpointResolver1.addOverrideEndpoint(serviceType, endpointUrl);
//				}
//			} else {
//				OverridableEndpointURLResolver endpointResolver = new OverridableEndpointURLResolver();
//				endpointResolver.addOverrideEndpoint(serviceType, endpointUrl);
//				this.config.withEndpointURLResolver(endpointResolver);
//			}
//		}
		return this;
	}

	public OSClientV3 credentials(String tokenId, String projectId) {

		Token token = new KeystoneToken();
		token.setId(tokenId);
		KeystoneProject project = new KeystoneProject();
		project.setId(projectId);
		token.setProjectInfo(project);
		this.token = token;
		return this;
	}

	public OSClientV3 withProjectId(String projectId) {
		this.projectId = projectId;
		if (null != token) {
			KeystoneProject project = new KeystoneProject();
			project.setId(projectId);
			token.setProjectInfo(project);
		}
		return this;
	}

	public OSClientV3 withToken(String tokenId) {

		Token token = new KeystoneToken();
		token.setId(tokenId);
		if (projectId != null) {
			KeystoneProject project = new KeystoneProject();
			project.setId(projectId);
			token.setProjectInfo(project);
		}
		this.token = token;
		return this;
	}

	@Override
	public OSClientV3 credentials(String user, String password, String domainId, String projectId, String authUrl) {
		return this.credentials(user, password, Identifier.byId(domainId), Identifier.byId(projectId), authUrl);
	}

	@Override
	public OSClientV3 credentials(String user, String password, Identifier domain, Identifier project, String authUrl) {
		this.user = user;
		this.password = password;
		this.domain = domain;
		this.authUrl = authUrl;
		this.scope = AuthScope.project(project);
		OSClientV3 client = authenticate();
		this.token = client.getToken();
		KeystoneProject projectInfo = new KeystoneProject();
		projectInfo.setId(project.getId());
		this.token.setProjectInfo(projectInfo);
		return this;
	}

	public OSClientV3 authenticate() {
		if (token != null && token.getId() != null && token.getId().length() > 0)
			return (OSClientV3) OSAuthenticator.invoke(new KeystoneAuth(token.getId(), scope), authUrl, perspective,
					config, provider);
		if (user != null && user.length() > 0)
			return (OSClientV3) OSAuthenticator.invoke(new KeystoneAuth(user, password, domain, scope), authUrl,
					perspective, config, provider);
		return (OSClientV3) OSAuthenticator.invoke(new KeystoneAuth(scope, Auth.Type.TOKENLESS), authUrl, perspective,
				config, provider);
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public AutoScalingService autoScaling() {
		return Apis.get(AutoScalingService.class);
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public ELBService loadBalancer() {
		return Apis.get(ELBService.class);
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public CloudTraceV1Service cloudTraceV1() {
		return Apis.get(CloudTraceV1Service.class);
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public CloudTraceV2Service cloudTraceV2() {
		return Apis.get(CloudTraceV2Service.class);
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public AntiDDoSServices antiDDoS() {
		return Apis.get(AntiDDoSServices.class);
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public NotificationService notification() {
		return Apis.get(NotificationService.class);
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public MessageQueueService messageQueue() {
		return Apis.get(MessageQueueService.class);
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public MaaSService maas() {
		return Apis.get(MaaSService.class);
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public DatabaseServices database() {
		return Apis.get(DatabaseServices.class);
	}
}
