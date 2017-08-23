/*******************************************************************************
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
package com.huawei.openstack4j.model.compute.actions;

import java.util.Iterator;
import java.util.Map;

import com.google.common.collect.Maps;

public class BaseActionOptions {

    private static final String OPT_FMT = "\"%s\": \"%s\"";
    
    public interface OptionEnum {
        String getParam();
    }
    
    private Map<OptionEnum, Object> options = Maps.newHashMap();

    protected BaseActionOptions() { }
    
    protected void add(OptionEnum option, Object value) {
        options.put(option, value);
    }
    
    @SuppressWarnings("unchecked")
    protected <T> T get(OptionEnum option) {
        return (T) options.get(option);
    }
    
    /**
     * @return A JSON String representing this object
     */
    public String toJsonString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        Iterator<OptionEnum> it = options.keySet().iterator();
        while (it.hasNext()) {
            OptionEnum opt = it.next();
            sb.append(String.format(OPT_FMT, opt.getParam(), options.get(opt)));
            if (it.hasNext())
                sb.append(",\n");
        }
        sb.append("\n}");
        return sb.toString();
    }
    
}
