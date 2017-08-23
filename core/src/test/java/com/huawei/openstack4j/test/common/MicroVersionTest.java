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

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.huawei.openstack4j.openstack.internal.MicroVersion;

import static org.testng.Assert.*;

public class MicroVersionTest {
    @DataProvider
    public static Object[][] invalidMicroVersions() {
        return new Object[][] {
                {"1.2.3"},
                {"1,0"},
                {"1"},
                {"version"},
                {"0.1"}
        };
    }

    @Test
    public void createFromString() {
        MicroVersion microVersion = new MicroVersion("1.1");
        assertEquals(microVersion, new MicroVersion(1, 1));
    }

    @Test(dataProvider = "invalidMicroVersions")
    public void createInvalidFromString(String v) {
        try {
            new MicroVersion(v);
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Invalid version pattern " + v + ", should be 'X.Y' (Major.Minor)");
        }
    }

    @Test
    public void equals() {
        MicroVersion v = new MicroVersion(1, 0);

        assertTrue(v.equals(new MicroVersion(1, 0)));
        assertTrue(v.equals(new MicroVersion("1.0")));
        assertTrue(v.equals(v));
        assertFalse(v.equals(new MicroVersion(1, 1)));
        assertFalse(v.equals(null));
        assertFalse(v.equals(new Object()));
    }

    @Test
    public void compareTo() {
        MicroVersion v10 = new MicroVersion(1, 0);
        MicroVersion v11 = new MicroVersion(1, 1);
        MicroVersion v20 = new MicroVersion(2, 0);

        assertTrue(v10.compareTo(v11) < 0);
        assertTrue(v20.compareTo(v11) > 0);
        assertTrue(v11.compareTo(v11) == 0);
    }


}
