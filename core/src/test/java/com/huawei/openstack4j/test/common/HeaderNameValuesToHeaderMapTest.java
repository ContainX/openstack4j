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
package com.huawei.openstack4j.test.common;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;

import com.huawei.openstack4j.model.common.header.HeaderNameValue;
import com.huawei.openstack4j.openstack.common.functions.HeaderNameValuesToHeaderMap;

/**
 * Tests HeaderNameValue based transformation
 * 
 * @author Jeremy Unruh
 *
 */
public class HeaderNameValuesToHeaderMapTest {

    private static List<HeaderNameValue> VALUES = Lists.newArrayList(
            new HeaderNameValue("Test 1", "Value 1"),
            new HeaderNameValue("Test 2", "Value 2"),
            new HeaderNameValue("Test 3", "Value 3"),
            new HeaderNameValue("Test 4", "Value 4")
    );
    
    @Test
    public void keyTest() {
        Map<String, Object> map = HeaderNameValuesToHeaderMap.INSTANCE.apply(VALUES);
        for (HeaderNameValue hnv : VALUES) {
            assertTrue(map.containsKey(hnv.getName()));
        }
    }
    
    @Test
    public void valueTest() {
        Map<String, Object> map = HeaderNameValuesToHeaderMap.INSTANCE.apply(VALUES);
        for (HeaderNameValue hnv : VALUES) {
            assertTrue(map.containsValue(hnv.getValue()));
        }
    }
    
}
