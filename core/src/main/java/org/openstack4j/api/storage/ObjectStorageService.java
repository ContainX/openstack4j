package org.openstack4j.api.storage;

import org.openstack4j.common.RestService;

/**
 * OpenStack Object Storage service
 * 
 * @author Jeremy Unruh
 */
public interface ObjectStorageService extends RestService {

    /**
     * The Object Storage Account Service
     * 
     * @return the account service
     */
    ObjectStorageAccountService account();
}