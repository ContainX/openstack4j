package org.openstack4j.openstack.compute.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.compute.BlockDeviceMappingCreate;
import org.openstack4j.model.compute.builder.BlockDeviceMappingBuilder;

/**
 *
 * @author jaroslav.sovicka@oracle.com
 */
public class NovaBlockDeviceMappingCreate implements BlockDeviceMappingCreate {

	public String device_name;
	public String source_type = "volume";
	public String destination_type = "volume";
	public String uuid;
	public String boot_index;
	public Integer volume_size;
	public boolean delete_on_termination = false;

	@JsonProperty("snapshot_id")
	public String snapshotId;

	@JsonProperty("volume_id")
	public String volumeId;

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
			create.boot_index = String.valueOf(i);
			return this;
		}

		@Override
		public BlockDeviceMappingBuilder sourceType(String type){
			create.source_type = type;
			return this;
		}

		@Override
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
		public BlockDeviceMappingCreate build() {
			return create;
		}

		@Override
		public BlockDeviceMappingBuilder from(BlockDeviceMappingCreate in) {
			return new NovaBlockDeviceMappingBuilder((NovaBlockDeviceMappingCreate) in);
		}
	}
}
