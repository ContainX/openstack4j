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
package com.huawei.openstack4j.model.image.v2;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.image.v2.domain.GlanceMember;

import java.util.Date;

/**
 * A Glance V2 Member
 * @author emjburns
 */
@JsonDeserialize(as = GlanceMember.class)
public interface Member extends ModelEntity {

    public enum MemberStatus {
        /**
         * Indicates to owner that image has not been updated.
         */
        PENDING,
        /**
         * Accept member status on image to see details of image.
         */
        ACCEPTED,
        /**
         * Functionally the same as pending, but indicates awareness of the
         * member request and specifically denies it.
         */
        REJECTED,
        /**
         * ImageStatus is not one of the reccognized statuses
         */
        UNKNOWN;

        @JsonCreator
        public static MemberStatus forValue(String value) {
            if (value != null)
            {
                for (MemberStatus s : MemberStatus.values()) {
                    if (s.name().equalsIgnoreCase(value)) {
                        return s;
                    }
                }
            }
            return MemberStatus.UNKNOWN;
        }

        @JsonValue
        public String value() {
            return name().toLowerCase();
        }
    }

    /**
     * An identifier for the image
     * Pattern: ^([0-9a-fA-F]){8}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){12}$
     * @return imageId
     */
    String getImageId();

    /**
     * An identifier for the image member (tenantId or projectId)
     * @return memberId
     */
    String getMemberId();

    /**
     * The date and time of image member creation
     * @return createdAt
     */
    Date getCreatedAt();

    /**
     * The date and time of last modification of image member
     * @return updatedAt
     */
    Date getUpdatedAt();

    /**
     * The status of the image member
     * @return status
     */
    MemberStatus getStatus();

    /**
     * The json schema for the member object
     * @return
     */
    String getSchema();
}
