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
package com.huawei.openstack4j.openstack.manila.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.huawei.openstack4j.model.manila.Share;
import com.huawei.openstack4j.model.manila.ShareManage;
import com.huawei.openstack4j.model.manila.builder.ShareManageBuilder;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Object to configure Shared File Systems to manage a share.
 *
 * @author Daniel Gonzalez Nothnagel
 */
@JsonRootName("share")
public class ManilaShareManage implements ShareManage {
    private Share.Protocol protocol;
    private String name;
    @JsonProperty("share_type")
    private String shareType;
    @JsonProperty("driver_options")
    private Map<String, String> driverOptions;
    @JsonProperty("export_path")
    private String exportPath;
    @JsonProperty("service_host")
    private String serviceHost;
    private String description;

    @Override
    public Share.Protocol getProtocol() {
        return protocol;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getShareType() {
        return shareType;
    }

    @Override
    public Map<String, String> getDriverOptions() {
        return driverOptions;
    }

    @Override
    public String getExportPath() {
        return exportPath;
    }

    @Override
    public String getServiceHost() {
        return serviceHost;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public static ShareManageBuilder builder() {
        return new ShareManageConcreteBuilder();
    }

    @Override
    public ShareManageBuilder toBuilder() {
        return new ShareManageConcreteBuilder(this);
    }
    
    public static class ShareManageConcreteBuilder implements ShareManageBuilder {
        ManilaShareManage shareManage;

        public ShareManageConcreteBuilder() {
            this(new ManilaShareManage());
        }

        public ShareManageConcreteBuilder(ManilaShareManage shareManage) {
            this.shareManage = shareManage;
        }

        @Override
        public ShareManageBuilder protocol(Share.Protocol protocol) {
            shareManage.protocol = protocol;
            return this;
        }

        @Override
        public ShareManageBuilder name(String name) {
            shareManage.name = name;
            return this;
        }

        @Override
        public ShareManageBuilder shareType(String shareType) {
            shareManage.shareType = shareType;
            return this;
        }

        @Override
        public ShareManageBuilder addDriverOption(String key, String value) {
            if (shareManage.driverOptions == null)
                shareManage.driverOptions = Maps.newHashMap();

            shareManage.driverOptions.put(key, value);
            return this;
        }

        @Override
        public ShareManageBuilder driverOptions(Map<String, String> driverOptions) {
            shareManage.driverOptions = driverOptions;
            return this;
        }

        @Override
        public ShareManageBuilder exportPath(String exportPath) {
            shareManage.exportPath = exportPath;
            return this;
        }

        @Override
        public ShareManageBuilder serviceHost(String serviceHost) {
            shareManage.serviceHost = serviceHost;
            return this;
        }

        @Override
        public ShareManageBuilder description(String description) {
            shareManage.description = description;
            return this;
        }

        @Override
        public ShareManage build() {
            return shareManage;
        }

        @Override
        public ShareManageBuilder from(ShareManage in) {
            shareManage = (ManilaShareManage) in;
            return this;
        }
    }
}
