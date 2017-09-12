package org.openstack4j.openstack.image.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Objects;
import org.openstack4j.model.image.CachedImage;
import org.openstack4j.openstack.common.CustomEpochToDateDeserializer;
import org.openstack4j.openstack.common.ListResult;

import java.util.Date;
import java.util.List;

/**
 * @author esommar on 5/8/2017.
 */
public class CachedGlanceImage implements CachedImage {

    private static final long serialVersionUID = 1L;

    @JsonProperty("image_id")
    private String imageId;

    private Integer hits;

    @JsonProperty("last_accessed")
    @JsonDeserialize(using = CustomEpochToDateDeserializer.class)
    private Date lastAccessed;

    @JsonDeserialize(using = CustomEpochToDateDeserializer.class)
    @JsonProperty("last_modified")
    private Date lastModified;

    private Long size;

    @Override
    public String getImageId() {
        return imageId;
    }

    @Override
    public Date getLastAccessed() {
        return lastAccessed;
    }

    @Override
    public Date getLastModified() {
        return lastModified;
    }

    @Override
    public Integer getHits() {
        return hits;
    }

    @Override
    public Long getSize() {
        return size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return Objects.toStringHelper(this).omitNullValues()
                .add("id", imageId).add("size", size).add("hits", hits).add("lastAccessed", lastAccessed)
                .add("lastModified", lastModified).addValue("\n")
                .toString();
    }

    public static class CachedImages extends ListResult<CachedGlanceImage> {

        private static final long serialVersionUID = 1L;
        @JsonProperty("cached_images")
        private List<CachedGlanceImage> cachedImages;

        @Override
        protected List<CachedGlanceImage> value() {
            return cachedImages;
        }
    }
}
