package org.openstack4j.openstack.heat.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.dataformat.yaml.snakeyaml.Yaml;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class Template {

    // template Resource is used for represent the template file or template URL
    private String tplContent;
    private Map<String, String> files = new HashMap<String, String>();
    private URL baseUrl;
    
    private final static String GET_FILE = "get_file";
    
    public Template(URL templateRes) throws JsonParseException, IOException{
        setTplContent(Resources.toString(templateRes, Charsets.UTF_8));
        baseUrl = TemplateUtils.baseUrl(templateRes.toString());
        getFileContents();
    }
    
    public Template(String templateLoc) 
            throws JsonParseException, MalformedURLException, 
                   UnsupportedEncodingException, IOException, URISyntaxException {
        this(TemplateUtils.normaliseFilePathToUrl(templateLoc));
    }

    /*
     * Processing the template file to find the "get_file" tag
     * Save the file name(absolute path) with file content in the files map
     */
    private void getFileContents() {
        Yaml yaml = new Yaml();
        @SuppressWarnings("unchecked")
        Map<String, Object> content = (Map<String, Object>) yaml.load(getTplContent());
        try {
            findGetFile(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void findGetFile(Map<?,?> map) throws IOException {
        for(Object key : map.keySet()){
            if(!(key instanceof String)) {
                continue;
            }
            
            if(isGetFile((String)key)) {
                String fileFullPath = baseUrl + (String)map.get(key);
                addToFiles(fileFullPath);
                continue;
            }
            
            Object subMap = map.get(key);
            if (subMap instanceof Map<?,?>){
                findGetFile((Map<?,?>)subMap);
            } else if (subMap instanceof List<?>) {
                for (Object item : (List<?>) subMap) {
                    if (item instanceof Map<?, ?>) {
                        findGetFile((Map<?, ?>) item);
                    }
                }
            }
        }
    }
    
    private void addToFiles(String filename) throws IOException {
        if(! files.containsKey(filename)) {
            files.put(filename, TemplateUtils.readToString(filename));
        }
    }
    
    private boolean isGetFile(String tag) {
        return tag.equals(GET_FILE);
    }
    
    
    public String getTplContent() {
        return tplContent;
    }

    public void setTplContent(String tplContent) {
        this.tplContent = tplContent;
    }

    public Map<String, String> getFiles() {
        return files;
    }

    public void setFiles(Map<String, String> files) {
        this.files = files;
    }
       
}
