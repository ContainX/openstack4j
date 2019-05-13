package org.openstack4j.openstack.octavia.domain;

import java.util.List;

import org.openstack4j.model.octavia.L7Policy;
import org.openstack4j.model.octavia.builder.L7PolicyBuilder;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.MoreObjects;

@JsonRootName("l7policy")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OctaviaL7Policy implements L7Policy {
	
	 private String id;
	 
	 private String name;
	 
	 @JsonProperty("listener_id")
	 private String listenerId;
	 
	 private String description;
	 
	 @JsonProperty("admin_state_up")
	 private boolean adminStateUp=true;
	 
	 private List<ListItem> rules;
	 
	 @JsonProperty("project_id")
	 private String projectId;
	 
	 @JsonProperty("provisioning_status")
	 private String provisioningStatus;
	 
	 @JsonProperty("redirect_pool_id")
	 private String redirectPoolId;
	 
	 @JsonProperty("redirect_url")
	 private String redirectUrl;
	 
	 private String action;
	 
	 private int position=1;
	 
	 @JsonProperty("operating_status")
	 private String operatingStatus;

	 @Override
	 public String getId()
	 {
		 return id;
	 }

	 @Override
	 public String getName()
	 {
		 return name;

	 }
	 
	 @Override
	 public String getListenerId()
	 {
		 return listenerId;

	 }
	 
	 @Override
	 public String getDescription()
	 {
		 return description;

	 }
	 
	 @Override
	 public boolean getAdminStateUp()
	 {
		 return adminStateUp;

	 }
	 
	 @Override
	 public List<ListItem> getRules()
	 {
		 return rules;

	 }
	 
	 @Override
	 public String getProjectId()
	 {
		 return projectId;

	 }
	 
	 @Override
	 public String getProvisioningStatus()
	 {
		 return provisioningStatus;

	 }
	 
	 @Override
	 public String getRedirectPoolId()
	 {
		 return redirectPoolId;

	 }
	 
	 @Override
	 public String getRedirectUrl()
	 {
		 return redirectUrl;

	 }
	 
	 @Override
	 public String getAction()
	 {
		 return action;

	 }
	 
	 @Override
	 public int getPosition()
	 {
		 return position;

	 }
	 
	 @Override
	 public String getOperatingStatus()
	 {
		 return operatingStatus;

	 }
	 
	 @Override
	 public L7PolicyBuilder toBuilder(){
		 return new L7PolicyConcreteBuilder(this);
	 }
	 
    public static class L7Policies extends ListResult<OctaviaL7Policy> {
        private static final long serialVersionUID = 1L;

        @JsonProperty("l7policies")
        List<OctaviaL7Policy> l7policies;

        @Override
        public List<OctaviaL7Policy> value() {
            return l7policies;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this).omitNullValues()
                    .add("l7policies", l7policies).toString();
        }
    }

	 
	 public static class L7PolicyConcreteBuilder implements L7PolicyBuilder {
		 private OctaviaL7Policy m;

	        public L7PolicyConcreteBuilder() {
	            this(new OctaviaL7Policy());
	        }

	        public L7PolicyConcreteBuilder(OctaviaL7Policy m) {
	            this.m = m;
	        }

	        @Override
	        public L7Policy build() {
	            return m;
	        }

	        @Override
	        public L7PolicyBuilder from(L7Policy in){
	            m = (OctaviaL7Policy) in;
	            return this;
	        }
	        
	        @Override
	        public L7PolicyBuilder action(String action)
	    	{
	        	m.action=action;
	        	return this;
	    	}

	        @Override
	        public L7PolicyBuilder adminStateUp(boolean adminStateUp)
	    	{
	        	m.adminStateUp=adminStateUp;
	        	return this;
	    	}

	        @Override
	        public L7PolicyBuilder description(String description)
	    	{
	        	m.description=description;
	        	return this;
	    	}

	        @Override
	        public L7PolicyBuilder listenerId(String listenerId)
	    	{
	        	m.listenerId=listenerId;
	        	return this;
	    	}

	        @Override
	        public L7PolicyBuilder position(int position)
	    	{
	        	m.position=position;
	        	return this;
	    	}

	        @Override
	        public L7PolicyBuilder projectId(String projectId)
	    	{
	        	m.projectId=projectId;
	        	return this;
	    	}

	        @Override
	        public L7PolicyBuilder redirectPoolId (String redirectPoolId )
	    	{
	        	m.redirectPoolId=redirectPoolId;
	        	return this;
	    	}
	    	
	        @Override
	        public L7PolicyBuilder redirectUrl (String redirectUrl )
	    	{
	        	m.redirectUrl=redirectUrl;
	        	return this;
	    	}
	        
	        @Override
	        public L7PolicyBuilder name (String name )
	    	{
	        	m.name=name;
	        	return this;
	    	}
	 }
    public static L7PolicyBuilder builder() {
        return new L7PolicyConcreteBuilder();
    }
	 
}
