package org.openstack4j.openstack.compute.domain;

import org.openstack4j.model.compute.BlockDeviceMappingCreate;
import org.openstack4j.model.compute.builder.BlockDeviceMappingBuilder;

/**
 *
 * @author jaroslav.sovicka@oracle.com
 */
public class NovaBlockDeviceMappingCreate implements BlockDeviceMappingCreate {
//    {
//                "device_name": "/dev/sda1",
//                "source_type": "volume",
//                "destination_type": "volume",
//                "uuid": "fake-volume-id-1",
//                "boot_index": "0"
//            }

	public String device_name;
	public String source_type = "volume";
	public String destination_type = "volume";
	public String uuid;
	public String boot_index;
	public boolean delete_on_termination = false;

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

		public BlockDeviceMappingBuilder deviceName(String deviceName) {
			create.device_name = deviceName;
			return this;
		}

		public BlockDeviceMappingBuilder uuid(String id) {
			create.uuid = id;
			return this;
		}

		public BlockDeviceMappingBuilder bootIndex(int i) {
			create.boot_index = String.valueOf(i);
			return this;
		}
		
		public BlockDeviceMappingBuilder sourceType(String type){
			create.source_type = type;
			return this;
		}
		
		public BlockDeviceMappingBuilder destinationType(String type){
			create.destination_type = type;
			return this;
		}
		
		@Override
		public BlockDeviceMappingBuilder deleteOnTermination(boolean deleteOnTermination) {
			create.delete_on_termination = deleteOnTermination;
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
