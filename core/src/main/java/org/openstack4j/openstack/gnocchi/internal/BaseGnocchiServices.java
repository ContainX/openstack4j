package org.openstack4j.openstack.gnocchi.internal;

import com.google.common.collect.Lists;
import org.openstack4j.api.types.ServiceType;
import org.openstack4j.openstack.internal.BaseOpenStackService;

import java.util.Collections;
import java.util.List;

/**
 * base class for gnocchi
 * @author zhangliang
 */
public class BaseGnocchiServices extends BaseOpenStackService {

    protected BaseGnocchiServices(){
        super(ServiceType.GNOCCHI);
    }

    protected <T> List<T> wrapList(T[] type) {
        if (type != null)
            return Lists.newArrayList(type);
        return Collections.emptyList();
    }

}
