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
package com.huawei.openstack4j.api.compute;

import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.model.common.Extension;

import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Test cases for Nova ExtensionList
 *
 * @author Jeremy Unruh
 */
@Test(suiteName="ExtensionList")
public class ExtensionTests extends AbstractTest {
    private static final String JSON_EXTENSIONS = "/compute/extensions.json";

    private static final DateFormat ISO8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");

    @Test
    public void testExtensions() throws Exception {
        respondWith(JSON_EXTENSIONS);

        List<? extends Extension> extensions = osv3().compute().listExtensions();
        assertEquals(2, extensions.size());

        Extension extension1 = extensions.get(0);
        Extension extension2 = extensions.get(1);

        assertEquals(extension1.getUpdated(), ISO8601.parse("2014-12-03T00:00:00Z"));
        assertEquals(extension1.getName(), "Multinic");
        assertTrue(extension1.getLinks().isEmpty());
        assertEquals(extension1.getNamespace(), URI.create("http://docs.openstack.org/compute/ext/fake_xml"));
        assertEquals(extension1.getAlias(), "NMN");
        assertEquals(extension1.getDescription(), "Multiple network support.");

        assertEquals(extension2.getUpdated(), ISO8601.parse("2014-12-03T00:00:00Z"));
        assertEquals(extension2.getName(), "DiskConfig");
        assertTrue(extension2.getLinks().isEmpty());
        assertEquals(extension2.getNamespace(), URI.create("http://docs.openstack.org/compute/ext/fake_xml"));
        assertEquals(extension2.getAlias(), "OS-DCF");
        assertEquals(extension2.getDescription(), "Disk Management Extension.");
    }

    @Override
    protected Service service() {
        return Service.COMPUTE;
    }
}
