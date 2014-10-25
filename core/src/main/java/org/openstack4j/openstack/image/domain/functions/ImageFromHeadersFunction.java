package org.openstack4j.openstack.image.domain.functions;

import static org.openstack4j.openstack.image.domain.ImageHeader.CHECKSUM;
import static org.openstack4j.openstack.image.domain.ImageHeader.CONTAINER_FORMAT;
import static org.openstack4j.openstack.image.domain.ImageHeader.CREATED_AT;
import static org.openstack4j.openstack.image.domain.ImageHeader.DELETED;
import static org.openstack4j.openstack.image.domain.ImageHeader.DELETED_AT;
import static org.openstack4j.openstack.image.domain.ImageHeader.DISK_FORMAT;
import static org.openstack4j.openstack.image.domain.ImageHeader.ID;
import static org.openstack4j.openstack.image.domain.ImageHeader.IS_PUBLIC;
import static org.openstack4j.openstack.image.domain.ImageHeader.MIN_DISK;
import static org.openstack4j.openstack.image.domain.ImageHeader.MIN_RAM;
import static org.openstack4j.openstack.image.domain.ImageHeader.NAME;
import static org.openstack4j.openstack.image.domain.ImageHeader.OWNER;
import static org.openstack4j.openstack.image.domain.ImageHeader.PROPERTY;
import static org.openstack4j.openstack.image.domain.ImageHeader.PROTECTED;
import static org.openstack4j.openstack.image.domain.ImageHeader.SIZE;
import static org.openstack4j.openstack.image.domain.ImageHeader.STATUS;
import static org.openstack4j.openstack.image.domain.ImageHeader.UPDATED_AT;
import static org.openstack4j.openstack.internal.Parser.asBoolean;
import static org.openstack4j.openstack.internal.Parser.asDate;
import static org.openstack4j.openstack.internal.Parser.asLong;

import java.util.Map;

import javax.annotation.Nullable;

import org.openstack4j.core.transport.HttpResponse;
import org.openstack4j.model.image.ContainerFormat;
import org.openstack4j.model.image.DiskFormat;
import org.openstack4j.model.image.Image;
import org.openstack4j.model.image.Image.Status;
import org.openstack4j.model.image.builder.ImageBuilder;
import org.openstack4j.openstack.image.domain.GlanceImage;

import com.google.common.base.Function;
import com.google.common.collect.Maps;

/**
 * When retrieving a specific image the Glance API returns headers in the response.  This function is responsible for parsing the headers into
 * an Image.
 * 
 * @author Jeremy Unruh
 */
public class ImageFromHeadersFunction implements Function<HttpResponse, Image> {

	private static final ImageFromHeadersFunction instance = new ImageFromHeadersFunction();
	
	public static ImageFromHeadersFunction instance() {
		return instance;
	}
	
	private ImageFromHeadersFunction() { }
	
	@Override
	@Nullable
	public Image apply(HttpResponse from) {
		
		ImageBuilder builder = GlanceImage.builder()
				    .id(from.header(ID.asHeader()))
				 		.name(from.header(NAME.asHeader()))
				 		.checksum(from.header(CHECKSUM.asHeader()))
				 		.minDisk(asLong(from.header(MIN_DISK.asHeader())))
				 		.minRam(asLong(from.header(MIN_RAM.asHeader())))
				 		.isPublic(asBoolean(from.header(IS_PUBLIC.asHeader())))
				 		.owner(from.header(OWNER.asHeader()))
				 		.containerFormat(ContainerFormat.value(from.header(CONTAINER_FORMAT.asHeader())))
				 		.diskFormat(DiskFormat.value(from.header(DISK_FORMAT.asHeader())));
		
		
		GlanceImage image = (GlanceImage) builder.build();
		image.location(from.header("Location"))
		     .isDeleted(asBoolean(from.header(DELETED.asHeader())))
		     .isProtected(asBoolean(from.header(PROTECTED.asHeader())))
		     .createdAt(asDate(from.header(CREATED_AT.asHeader())))
		     .updatedAt(asDate(from.header(UPDATED_AT.asHeader())))
		     .deletedAt(asDate(from.header(DELETED_AT.asHeader())))
		     .size(asLong(from.header(SIZE.asHeader())))
		     .status(Status.value(from.header(STATUS.asHeader())));
		
		Map<String, String> properties = null;
		String property = PROPERTY.asHeader()+"-";
		for (String k : from.headers().keySet()) {
			if (k.indexOf(property) > -1)
			{
				if (properties == null)
					properties = Maps.newHashMap();
				properties.put(k.substring(property.length()).toLowerCase(), from.headers().get(k));
			}
		}
		image.properties(properties);
		
		return image;
	}
}
