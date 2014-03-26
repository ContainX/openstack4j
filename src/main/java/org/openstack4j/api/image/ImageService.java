package org.openstack4j.api.image;

import java.io.InputStream;
import java.util.List;

import org.openstack4j.common.RestService;
import org.openstack4j.model.image.Image;

/**
 * OpenStack (Glance) Image based Operations
 * 
 * @author Jeremy Unruh
 */
public interface ImageService extends RestService {

	/**
	 * Lists all public VM images
	 * 
	 * @return list of images or empty
	 */
	List<? extends Image> list();
	
	/**
	 * Gets an Image by ID
	 * 
	 * @param imageId the image identifier
	 * @return the image or null if not found
	 */
	Image get(String imageId);

	/**
	 * Deletes an Image by ID
	 * 
	 * @param imageId the image identifier
	 */
	void delete(String imageId);
	
	/**
	 * Updates an Image.  The image must have the id set or a validation exception will be thrown
	 * 
	 * @param image the image to update
	 * @return the updated image
	 */
	Image update(Image image);
	
	/**
	 * Return the image date for the image by ID
	 * @param imageId the image identifier
	 * @return the input stream or null if not found
	 */
	InputStream getAsStream(String imageId);
	
}
