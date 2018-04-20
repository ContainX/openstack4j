package org.openstack4j.openstack.bareMetal.internal;

import org.openstack4j.api.bareMetal.ChassisService;
import org.openstack4j.model.bareMetal.Chassis;
import org.openstack4j.model.bareMetal.ChassisUpdate;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.openstack.bareMetal.domain.BareMetalChassis;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Chassis Operation API implementation
 *
 * @author zhangliang
 */
public class ChassisServiceImpl extends BaseBareMetalServices implements ChassisService {

    @Override
    public Chassis get(String chassisId) {
        checkNotNull(chassisId);
        return get(BareMetalChassis.class, uri("/chassis/%s", chassisId)).execute();
    }

    @Override
    public List<? extends Chassis> list() {
        return get(BareMetalChassis.Chassises.class, uri("/chassis")).execute().getList();
    }

    @Override
    public Chassis create(Chassis chassis) {
        checkNotNull(chassis);
        return post(BareMetalChassis.class, uri("/chassis")).entity(chassis).execute();
    }

    @Override
    public ActionResponse delete(String chassisId) {
        checkNotNull(chassisId);
        return deleteWithResponse(uri("/chassis/%s", chassisId)).execute();
    }

    @Override
    public Chassis update(String chassisId, ChassisUpdate chassisUpdate) {
        checkNotNull(chassisId);
        checkNotNull(chassisUpdate, "chassisUpdate");
        return patch(BareMetalChassis.class, uri("/chassis/%s", chassisId)).entity(chassisUpdate).execute();
    }
}
