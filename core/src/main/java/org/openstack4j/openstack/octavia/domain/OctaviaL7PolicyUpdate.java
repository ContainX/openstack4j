package org.openstack4j.openstack.octavia.domain;

import org.openstack4j.model.octavia.L7PolicyUpdate;
import org.openstack4j.model.octavia.builder.L7PolicyUpdateBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("l7policy")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OctaviaL7PolicyUpdate implements L7PolicyUpdate {
	
	 private String name;
	
	 private String description;
	 
	 @JsonProperty("admin_state_up")
	 private boolean adminStateUp=true;
	 
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
	 public String getName()
	 {
		 return name;
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
	 
	 
	

	public void setName(String name) {
		this.name = name;
	}




	public static class L7PolicyUpdateConcreteBuilder implements L7PolicyUpdateBuilder {

	        private OctaviaL7PolicyUpdate m;

	        public L7PolicyUpdateConcreteBuilder (){
	            this(new OctaviaL7PolicyUpdate());
	        }

	        public L7PolicyUpdateConcreteBuilder (OctaviaL7PolicyUpdate m){
	            this.m = m;
	        }

	        @Override
	        public L7PolicyUpdate build(){
	            return m;
	        }

	        @Override
	        public L7PolicyUpdateBuilder from(L7PolicyUpdate in){
	            m = (OctaviaL7PolicyUpdate) in;
	            return this;
	        }

	        
	        @Override
	        public L7PolicyUpdateBuilder action(String action)
	    	{
	        	m.action=action;
	        	return this;
	    	}

	        @Override
	        public L7PolicyUpdateBuilder adminStateUp(boolean adminStateUp)
	    	{
	        	m.adminStateUp=adminStateUp;
	        	return this;
	    	}

	        @Override
	        public L7PolicyUpdateBuilder description(String description)
	    	{
	        	m.description=description;
	        	return this;
	    	}

	        @Override
	        public L7PolicyUpdateBuilder position(int position)
	    	{
	        	m.position=position;
	        	return this;
	    	}

	        @Override
	        public L7PolicyUpdateBuilder redirectPoolId (String redirectPoolId )
	    	{
	        	m.redirectPoolId=redirectPoolId;
	        	return this;
	    	}
	    	
	        @Override
	        public L7PolicyUpdateBuilder redirectUrl (String redirectUrl )
	    	{
	        	m.redirectUrl=redirectUrl;
	        	return this;
	    	}
	        
	        @Override
	        public L7PolicyUpdateBuilder name (String name )
	    	{
	        	m.name=name;
	        	return this;
	    	}

	    }
	    public static L7PolicyUpdateBuilder builder(){
	        return new L7PolicyUpdateConcreteBuilder();
	    }

	    @Override
	    public L7PolicyUpdateBuilder toBuilder(){
	        return new L7PolicyUpdateConcreteBuilder();
	    }
}
