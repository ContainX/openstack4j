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
package com.huawei.openstack4j.model.compute.ext;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * DNS Record Types
 * 
 * @author Jeremy Unruh
 */
public enum DNSRecordType {

    /**  a host address */
    A,
    /**  an authoritative name server */
    NS,
    /**  a mail destination (Obsolete - use MX) */
    MD,
    /**  a mail forwarder (Obsolete - use MX) */
    MF,
    /**  the canonical name for an alias */
    CNAME,
    /**  marks the start of a zone of authority */
    SOA,
    /**  a mailbox domain name (EXPERIMENTAL) */
    MB,
    /**  a mail group member (EXPERIMENTAL) */
    MG,
    /**  a mail rename domain name (EXPERIMENTAL) */
    MR,
    /**  a null RR (EXPERIMENTAL) */
    NULL,
    /**  a well known service description */
    WKS,
    /**  a domain name pointer */
    PTR,
    /**  host information */
    HINFO,
    /**  mailbox or mail list information */
    MINFO,
    /**  mail exchange */
    MX,
    /**  text strings */
    TXT
    ;
    
    @JsonValue
    public String value() {
        return name();
    }
    
    @JsonCreator
    public static DNSRecordType forValue(String value) {
        if (value != null)
        {
            for (DNSRecordType rt : DNSRecordType.values()) {
                if (rt.name().equalsIgnoreCase(value))
                    return rt;
            }
        }
        return DNSRecordType.A;
    }
}
