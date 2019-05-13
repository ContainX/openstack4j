package org.openstack4j.openstack.octavia.domain;

import java.util.List;

import org.openstack4j.model.octavia.L7Rule;
import org.openstack4j.model.octavia.builder.L7RuleBuilder;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.base.MoreObjects;

@JsonRootName("rule")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OctaviaL7Rule implements L7Rule{
	
	 @JsonProperty("compare_type")
	 private String compareType;
	 
	 @JsonProperty("provisioning_status") 
	 private String provisioningStatus;
	 
	 private boolean invert;
	 
	 @JsonProperty("admin_state_up") 
	 private boolean adminStateUp=true;
	 
	 private String value;
	 
	 private String key;
	 
	 @JsonProperty("project_id") 
	 private String projectId;
	 
	 private String type;
	 
	 private String id;
	 
	 @JsonProperty("operating_status") 
	 private String operatingStatus;
	 
	 private List<String> tags;
	 
	 @JsonProperty("l7policy_id") 
	 private String l7policyId;
	 
	 @Override
	 public String getCompareType()
	 {
		 return compareType;
	 }
	 
	 @Override
	 public String getProvisioningStatus()
	 {
		 return provisioningStatus;
	 }
	 
	 @Override
	 public boolean getInvert()
	 {
		 return invert;
	 }
	 
	 @Override
	 public boolean getAdminStateUp()
	 {
		 return adminStateUp;
	 }
	 
	 @Override
	 public String getValue()
	 {
		 return value;
	 }
	 
	 @Override
	 public String getKey()
	 {
		 return key;
	 }
	 
	 @Override
	 public String getProjectId()
	 {
		 return projectId;
	 }
	 
	 @Override
	 public String getType()
	 {
		 return type;
	 }
	 
	 @Override
	 public String getId()
	 {
		 return id;
	 }
	 
	 @Override
	 public String getOperatingStatus()
	 {
		 return operatingStatus;
	 }
	 
	 @Override
	 public List<String> getTags()
	 {
		 return tags;
	 }
	 
	 @Override
	 public String getL7PolicyId()
	 {
		 return l7policyId;
	 }
	 
	 @Override
	 public L7RuleBuilder toBuilder(){
		 return new L7RuleConcreteBuilder(this);
	 }
	 
	  public static class L7Rules extends ListResult<OctaviaL7Rule> {
	        private static final long serialVersionUID = 1L;

	        @JsonProperty("rules")
	        List<OctaviaL7Rule> rules;

	        @Override
	        public List<OctaviaL7Rule> value() {
	            return rules;
	        }

	        @Override
	        public String toString() {
	            return MoreObjects.toStringHelper(this).omitNullValues()
	                    .add("rules", rules).toString();
	        }
	    }
	  public static class L7RuleConcreteBuilder implements L7RuleBuilder {
			 private OctaviaL7Rule m;

		        public L7RuleConcreteBuilder() {
		            this(new OctaviaL7Rule());
		        }

		        public L7RuleConcreteBuilder(OctaviaL7Rule m) {
		            this.m = m;
		        }

		        @Override
		        public L7Rule build() {
		            return m;
		        }

		        @Override
		        public L7RuleBuilder from(L7Rule in){
		            m = (OctaviaL7Rule) in;
		            return this;
		        }
		        
		        @Override
		    	public L7RuleBuilder adminStateUp(boolean adminStateUp)
		    	{
		        	m.adminStateUp=adminStateUp;
		        	return this;
		    	}

		        @Override
		        public L7RuleBuilder compareType(String compareType)
		    	{
		        	m.compareType=compareType;
		        	return this;
		    	}
		        
		        @Override
		        public L7RuleBuilder invert(boolean invert)
		    	{
		        	m.invert=invert;
		        	return this;
		    	}
		        
		        @Override
		        public L7RuleBuilder key(String key)
		    	{
		        	m.key=key;
		        	return this;
		    	}
		        
		        
		        @Override
		        public L7RuleBuilder projectId(String projectId)
		    	{
		        	m.projectId=projectId;
		        	return this;
		    	}
		        
		        @Override
		        public L7RuleBuilder tags(List<String> tags)
		    	{
		        	m.tags=tags;
		        	return this;
		    	}
		        
		        @Override
		        public L7RuleBuilder type(String type)
		    	{
		        	m.type=type;
		        	return this;
		    	}
		        
		        @Override		        
		        public L7RuleBuilder value(String value)
		    	{
		        	m.value=value;
		        	return this;
		    	}
		        
		 }
	    public static L7RuleBuilder builder() {
	        return new L7RuleConcreteBuilder();
	    }	 
}
