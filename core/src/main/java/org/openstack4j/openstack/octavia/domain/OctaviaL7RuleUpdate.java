package org.openstack4j.openstack.octavia.domain;

import java.util.List;

import org.openstack4j.model.octavia.L7RuleUpdate;
import org.openstack4j.model.octavia.builder.L7RuleUpdateBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("rule")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OctaviaL7RuleUpdate implements L7RuleUpdate {
	 @JsonProperty("compare_type")
	 private String compareType;
	 
	 private boolean invert;
	 
	 @JsonProperty("admin_state_up") 
	 private boolean adminStateUp=true;
	 
	 private String value;
	 
	 private String key;
	 
	 private String type;
	 
	 private List<String> tags;
	 
	 @Override
	 public String getCompareType()
	 {
		 return compareType;
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
	 public String getType()
	 {
		 return type;
	 }
	 
	 @Override
	 public List<String> getTags()
	 {
		 return tags;
	 }
	 
	  public static class L7RuleUpdateConcreteBuilder implements L7RuleUpdateBuilder {

	        private OctaviaL7RuleUpdate m;

	        public L7RuleUpdateConcreteBuilder (){
	            this(new OctaviaL7RuleUpdate());
	        }

	        public L7RuleUpdateConcreteBuilder (OctaviaL7RuleUpdate m){
	            this.m = m;
	        }

	        @Override
	        public L7RuleUpdate build(){
	            return m;
	        }

	        @Override
	        public L7RuleUpdateBuilder from(L7RuleUpdate in){
	            m = (OctaviaL7RuleUpdate) in;
	            return this;
	        }
	        
	        @Override
	    	public L7RuleUpdateBuilder adminStateUp(boolean adminStateUp)
	    	{
	        	m.adminStateUp=adminStateUp;
	        	return this;
	    	}

	        @Override
	        public L7RuleUpdateBuilder compareType(String compareType)
	    	{
	        	m.compareType=compareType;
	        	return this;
	    	}
	        
	        @Override
	        public L7RuleUpdateBuilder invert(boolean invert)
	    	{
	        	m.invert=invert;
	        	return this;
	    	}
	        
	        @Override
	        public L7RuleUpdateBuilder key(String key)
	    	{
	        	m.key=key;
	        	return this;
	    	}
	        
	        @Override
	        public L7RuleUpdateBuilder tags(List<String> tags)
	    	{
	        	m.tags=tags;
	        	return this;
	    	}
	        
	        @Override
	        public L7RuleUpdateBuilder type(String type)
	    	{
	        	m.type=type;
	        	return this;
	    	}
	        
	        @Override		        
	        public L7RuleUpdateBuilder value(String value)
	    	{
	        	m.value=value;
	        	return this;
	    	}

	        
	       

	    }
	    public static L7RuleUpdateBuilder builder(){
	        return new L7RuleUpdateConcreteBuilder();
	    }

	    @Override
	    public L7RuleUpdateBuilder toBuilder(){
	        return new L7RuleUpdateConcreteBuilder();
	    }
}
