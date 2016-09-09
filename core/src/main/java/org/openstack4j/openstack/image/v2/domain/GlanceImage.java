package org.openstack4j.openstack.image.v2.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import org.openstack4j.model.common.builder.BasicResourceBuilder;
import org.openstack4j.model.image.v2.ContainerFormat;
import org.openstack4j.model.image.v2.DiskFormat;
import org.openstack4j.model.image.v2.Image;
import org.openstack4j.model.image.v2.builder.ImageBuilder;
import org.openstack4j.openstack.common.ListResult;

import java.util.Date;
import java.util.List;

/**
 * A glance v2.0-2.3 image model implementation
 *
 * @author emjburns
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GlanceImage implements Image {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private List<String> tags;

    private ImageStatus imageStatus;

    @JsonProperty("container_format")
    private ContainerFormat containerFormat;

    @JsonProperty("disk_format")
    private DiskFormat diskFormat;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("updated_at")
    private Date updatedAt;

    @JsonProperty("min_disk")
    private Integer minDisk;

    @JsonProperty("min_ram")
    private Integer minRam;

    @JsonProperty("protected")
    private Boolean isProtected;

    private String checksum;

    private String owner;

    private ImageVisibility visibility;

    private Integer size;

    private List<String> locations;

    @JsonProperty("direct_url")
    private String directUrl;

    private String self;

    private String file;

    private String schema;

    private String architecture;

    @JsonProperty("instance_uuid")
    private String instanceUuid;

    @JsonProperty("kernel_id")
    private String kernelId;

    @JsonProperty("os_version")
    private String osVersion;

    @JsonProperty("os_distro")
    private String osDistro;

    @JsonProperty("ramdisk_id")
    private String ramdiskId;

    @JsonProperty("virtual_size")
    private Integer virtualSize;


    /**
     * {@inheritDoc}
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImageStatus getStatus() {
        return imageStatus;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getTags() {
        return tags;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContainerFormat getContainerFormat() {
        return containerFormat;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DiskFormat getDiskFormat() {
        return diskFormat;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getMinDisk() {
        return minDisk;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean getIsProtected() {
        return isProtected;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getMinRam() {
        return minRam;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getChecksum() {
        return checksum;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getOwner() {
        return owner;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImageVisibility getVisibility() {
        return visibility;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getSize() {
        return size;
    }

    @Override
    public List<String> getLocations() {
        return locations;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDirectUrl() {
        return directUrl;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSelf() {
        return self;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFile() {
        return file;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSchema() {
        return schema;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRamdiskId() {
        return ramdiskId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getOsDistro() {
        return osDistro;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getOsVersion() {
        return osVersion;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getKernelId() {
        return kernelId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInstanceUuid() {
        return instanceUuid;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getArchitecture() {
        return architecture;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getVirtualSize() {
        return virtualSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImageBuilder toBuilder() {
        return new ImageConcreteBuilder(this);
    }

    public static ImageBuilder builder() {
        return new ImageConcreteBuilder();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("tags", tags)
                .add("imageStatus", imageStatus)
                .add("containerFormat", containerFormat)
                .add("diskFormat", diskFormat)
                .add("createdAt", createdAt)
                .add("updatedAt", updatedAt)
                .add("minDisk", minDisk)
                .add("minRam", minRam)
                .add("isProtected", isProtected)
                .add("checksum", checksum)
                .add("owner", owner)
                .add("visibility", visibility)
                .add("size", size)
                .add("locations", locations)
                .add("directUrl", directUrl)
                .add("self", self)
                .add("file", file)
                .add("schema", schema)
                .add("architecture", architecture)
                .add("instanceUuid", instanceUuid)
                .add("kernelId", kernelId)
                .add("osVersion", osVersion)
                .add("osDistro", osDistro)
                .add("ramdiskId", ramdiskId)
                .add("virtualSize", virtualSize)
                .toString();
    }


    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Images extends ListResult<GlanceImage> {
        private static final long serialVersionUID = 1L;
        @JsonProperty("images")
        private List<GlanceImage> images;

        @Override
        protected List<GlanceImage> value() {
            return images;
        }
    }

    public static class ImageConcreteBuilder extends BasicResourceBuilder<Image, ImageConcreteBuilder> implements ImageBuilder {
        private GlanceImage m;

        ImageConcreteBuilder() {
            this(new GlanceImage());
        }

        ImageConcreteBuilder(GlanceImage m) {
            this.m = m;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ImageBuilder visibility(ImageVisibility visibility) {
            m.visibility = visibility;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ImageBuilder tags(List<String> tags) {
            m.tags = tags;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ImageBuilder containerFormat(ContainerFormat containerFormat) {
            m.containerFormat = containerFormat;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ImageBuilder diskFormat(DiskFormat diskFormat) {
            m.diskFormat = diskFormat;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ImageBuilder minDisk(Integer minDisk) {
            m.minDisk = minDisk;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ImageBuilder minRam(Integer minRam) {
            m.minRam = minRam;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ImageBuilder isProtected(Boolean isProtected) {
            m.isProtected = isProtected;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ImageBuilder architecture(String architecture) {
            m.architecture = architecture;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ImageBuilder instanceUuid(String instanceUuid) {
            m.instanceUuid = instanceUuid;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ImageBuilder kernelId(String kernelId) {
            m.kernelId = kernelId;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ImageBuilder osVersion(String osVersion) {
            m.osVersion = osVersion;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ImageBuilder osDistro(String osDistro) {
            m.osDistro = osDistro;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ImageBuilder ramdiskId(String ramdiskId) {
            m.ramdiskId = ramdiskId;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Image build() {
            return m;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ImageBuilder from(Image in) {
            m = (GlanceImage) in;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected Image reference() {
            return m;
        }
    }
}
