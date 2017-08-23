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
package com.huawei.openstack4j.openstack.heat.utils;

import static com.huawei.openstack4j.core.transport.ClientConstants.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class TemplateUtils {

    public static String readToString(String filename) throws IOException {
        return Resources.toString(new URL(filename), Charsets.UTF_8);
    }
    
    public static String readToString(URL url) throws IOException {
        return Resources.toString(url, Charsets.UTF_8);
    }
    
    public static URL baseUrl(String url) throws MalformedURLException {
        String baseUrl =  url.substring(0, url.lastIndexOf(URI_SEP) + 1);
        if(! baseUrl.endsWith(URI_SEP)) {
            baseUrl += URI_SEP;
        }
        return new URL(baseUrl);
    }
    
    public static URL normaliseFilePathToUrl(String path) 
            throws MalformedURLException, URISyntaxException {
        if(path.startsWith("file:") 
                || path.startsWith("http:") 
                || path.startsWith("https:")) {
            return new URI(path).toURL();
        } else {
            return new File(path).toURI().toURL();
        }
        
    }

    public static URL normaliseFilePathToUrl(String baseUrl, String templateName)
            throws MalformedURLException, URISyntaxException {
        if (templateName.startsWith("file:")
                || templateName.startsWith("http:")
                || templateName.startsWith("https:")) {
            return normaliseFilePathToUrl(templateName);
        } else {
            return normaliseFilePathToUrl(baseUrl + templateName);
        }
    }
}
