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
package com.huawei.openstack4j.core.transport;


/**
 * Proxy host configuration
 * 
 * @author Jeremy Unruh
 */
public final class ProxyHost {

    private String host;
    private int port;
    private String username;
    private String password;
    
    public ProxyHost(String host, int port) {
        this(host, port, null, null);
    }

    private ProxyHost(String host, int port, String username, String password) {
        super();
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }
    
    /**
     * Creates a new ProxyHost configuration
     * 
     * @param host the proxy host (ex. http://myproxy)
     * @param port the proxy port (ex. 8080)
     * @return ProxyHost
     */
    public static ProxyHost of(String host, int port) {
        return new ProxyHost(host, port);
    }
    
    /**
     * Creates a new ProxyHost configuration with credentials
     * 
     * @param host the proxy host (ex. http://myproxy)
     * @param port the proxy port (ex. 8080)
     * @param username the username for proxy authentication
     * @param password the password for proxy authentication
     * @return ProxyHost
     */
    public static ProxyHost of(String host, int port, String username, String password) {
        return new ProxyHost(host, port, username, password);
    }

    public String getHost() {
        return host;
    }
    
    public String getRawHost() {
        if (host != null && host.startsWith("http")) {
            return host.substring(host.indexOf("://")+3);
        }
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    public String getHostWithPort() {
        return String.format("%s:%d", host, port);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((host == null) ? 0 : host.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + port;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProxyHost other = (ProxyHost) obj;
        if (host == null) {
            if (other.host != null)
                return false;
        } else if (!host.equals(other.host))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (port != other.port)
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }
    
    
}
