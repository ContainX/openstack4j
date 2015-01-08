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
            resolveTempalteGetFiles(content);
            resolveTemplateType(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void resolveTemplateType(Map<?,?> map) throws MalformedURLException, IOException {
        for(Object key : map.keySet()) {
            // Ignore if the key is not string. Actually not happening
            if(!(key instanceof String)) {
                continue;
            } 
            
            String skey = (String) key;
            Object value = map.get(skey);

            if(value instanceof String) {
                String valueInString = (String) value;
                //Processing the nested template
                if(isTemplate(skey, valueInString)) {
                    try {
                        URL templateName =  TemplateUtils.normaliseFilePathToUrl(baseUrl + valueInString);

                        if(! files.containsKey(templateName.toString())) {
                            Template tpl = new Template(templateName);
                            files.put(templateName.toString(),tpl.getTplContent());
                            files.putAll(tpl.getFiles());
                        }
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
            }
            
            
            if (value instanceof Map<?,?>){
                resolveTemplateType((Map<?,?>)value);
            } else if (value instanceof List<?>) {
                for (Object item : (List<?>) value) {
                    if (item instanceof Map<?, ?>) {
                        resolveTemplateType((Map<?, ?>) item);
                    }
                }
            }
        }
    }
    
    private void resolveTempalteGetFiles(Map<?,?> map) throws IOException  {
        for(Object key : map.keySet()){
            // Ignore if the key is not string. Actually not happening
            if(!(key instanceof String)) {
                continue;
            }
            
            String skey = (String) key;
            Object value = map.get(skey);
            
            if(isGetFile(skey)) {
                //if key="get_file", the value is the filename
                String fileFullPath = baseUrl + (String)value;
                addToFiles(fileFullPath);
                continue;
            }
            
            Object subMap = map.get(skey);
            if (subMap instanceof Map<?,?>){
                resolveTempalteGetFiles((Map<?,?>)subMap);
            } else if (subMap instanceof List<?>) {
                for (Object item : (List<?>) subMap) {
                    if (item instanceof Map<?, ?>) {
                        resolveTempalteGetFiles((Map<?, ?>) item);
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
    
    private boolean isTemplate(String key, String value) {
        if (! key.equals("type")) {
            return false;
        } 
        if (value.endsWith(".yaml") || value.endsWith(".template")) {
            return true;
        } else {
            return false;
        }
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
