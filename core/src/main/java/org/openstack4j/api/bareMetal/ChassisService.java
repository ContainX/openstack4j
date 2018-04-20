package org.openstack4j.api.bareMetal;

import org.openstack4j.model.bareMetal.Chassis;
import org.openstack4j.model.bareMetal.ChassisUpdate;
import org.openstack4j.model.common.ActionResponse;

import java.util.List;

/**
 * bare metal chassis service
 *
 * @author zhangliang
 */
public interface ChassisService {

    /**
     * Show chassis details
     * @param chassisId
     * @return Chassis
     */
    Chassis get(String chassisId);

    /**
     * List chassis with details
     * @return List of Chassis
     */
    List<? extends Chassis> list();

    /**
     * Create chassis
     * @param chassis
     * @return Chassis
     */
    Chassis create(Chassis chassis);

    /**
     * Delete chassis
     * @param chassisId
     * @return ActionResponse
     */
    ActionResponse delete(String chassisId);

    /**
     * Update chassis
     * @param chassisId
     * @param chassisUpdate
     * @return Chassis
     */
    Chassis update(String chassisId, ChassisUpdate chassisUpdate);

}
