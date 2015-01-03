package org.openstack4j.openstack.heat.utils;

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
        String baseUrl =  url.substring(0, url.lastIndexOf("/") + 1);
        if(! baseUrl.endsWith("/")) {
            baseUrl += "/";
        }
        return new URL(baseUrl);
    }
    
    public static URL normaliseFilePathToUrl(String path) 
            throws MalformedURLException, URISyntaxException {
        if(path.startsWith("file:") || path.startsWith("http:")) {
            return new URI(path).toURL();
        } else {
            return new File(path).toURI().toURL();
        }
        
    }
}
