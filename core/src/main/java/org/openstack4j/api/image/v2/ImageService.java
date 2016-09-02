package org.openstack4j.api.image.v2;

/**
 * OpenStack (Glance) Image V2 support
 * @author emjburns
 */
public interface ImageService {

    /**
     * @return the image v2 images service
     */
    ImagesService images();

    /**
     * @return the image v2 tasks service
     */
    TaskService tasks();
}
