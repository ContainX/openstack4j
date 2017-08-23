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
package com.huawei.openstack4j.openstack.compute.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.huawei.openstack4j.model.compute.BDMDestType;
import com.huawei.openstack4j.model.compute.BDMSourceType;
import com.huawei.openstack4j.model.compute.BlockDeviceMappingCreate;
import com.huawei.openstack4j.model.compute.builder.BlockDeviceMappingBuilder;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author jaroslav.sovicka@oracle.com
 */
public class NovaBlockDeviceMappingCreate implements BlockDeviceMappingCreate {

	public String device_name;
	public BDMSourceType source_type = BDMSourceType.VOLUME;
	public BDMDestType destination_type = BDMDestType.VOLUME;
	public String uuid;
	public Integer boot_index;
	public Integer volume_size;
	public boolean delete_on_termination = false;

	@JsonProperty("snapshot_id")
	public String snapshotId;

	@JsonProperty("volume_id")
	public String volumeId;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("disk_bus")
    public String diskBus;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("device_type")
    public String deviceType;

	public static NovaBlockDeviceMappingBuilder builder() {
		return new NovaBlockDeviceMappingBuilder(new NovaBlockDeviceMappingCreate());
	}


	@Override
	public NovaBlockDeviceMappingBuilder toBuilder() {
		return new NovaBlockDeviceMappingBuilder(this);
	}

	public static class NovaBlockDeviceMappingBuilder implements BlockDeviceMappingBuilder {

		NovaBlockDeviceMappingCreate create;

		public NovaBlockDeviceMappingBuilder(NovaBlockDeviceMappingCreate create) {
			this.create = create;
		}

		@Override
		public BlockDeviceMappingBuilder deviceName(String deviceName) {
			create.device_name = deviceName;
			return this;
		}

		@Override
		public BlockDeviceMappingBuilder uuid(String id) {
			create.uuid = id;
			return this;
		}

		@Override
		public BlockDeviceMappingBuilder bootIndex(int i) {
			create.boot_index = i;
			return this;
		}

		@Override
		public BlockDeviceMappingBuilder sourceType(BDMSourceType type){
			create.source_type = type;
			return this;
		}

		@Override
		public BlockDeviceMappingBuilder destinationType(BDMDestType type){
			create.destination_type = type;
			return this;
		}

		@Override
		public BlockDeviceMappingBuilder deleteOnTermination(boolean deleteOnTermination) {
			create.delete_on_termination = deleteOnTermination;
			return this;
		}

		@Override
		public BlockDeviceMappingBuilder snapshotId(String snapshotId) {
		    create.snapshotId = snapshotId;
		    return this;
		}

		@Override
		public BlockDeviceMappingBuilder volumeId(String volumeId) {
		    create.volumeId = volumeId;
		    return this;
		}
		
		@Override
        public BlockDeviceMappingBuilder volumeSize(Integer volumeSize) {
            create.volume_size = volumeSize;
            return this;
        }

        @Override
        public BlockDeviceMappingBuilder diskBus(String diskBus) {
            create.diskBus = diskBus;
            return this;
        }

        @Override
        public BlockDeviceMappingBuilder deviceType(String deviceType) {
            create.deviceType = deviceType;
            return this;
        }

		@Override
		public BlockDeviceMappingCreate build() {
			return create;
		}

		@Override
		public BlockDeviceMappingBuilder from(BlockDeviceMappingCreate in) {
			return new NovaBlockDeviceMappingBuilder((NovaBlockDeviceMappingCreate) in);
		}
	}
}
