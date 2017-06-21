/*******************************************************************************
 *  Copyright 2017 HuaWei TLD
 * 	Copyright 2016 ContainX and OpenStack4j                                          
 * 	                                                                                 
 * 	Licensed under the Apache License, Version 2.0 (the "License"); you may not      
 * 	use this file except in compliance with the License. You may obtain a copy of    
 * 	the License at                                                                   
 * 	                                                                                 
 * 	    http://www.apache.org/licenses/LICENSE-2.0                                   
 * 	                                                                                 
 * 	Unless required by applicable law or agreed to in writing, software              
 * 	distributed under the License is distributed on an "AS IS" BASIS, WITHOUT        
 * 	WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the         
 * 	License for the specific language governing permissions and limitations under    
 * 	the License.                                                                     
 *******************************************************************************/
package org.openstack4j.openstack.dns.v2.internal;

import static com.google.common.base.Preconditions.*;
import static com.google.common.collect.Maps.*;
import static org.openstack4j.core.transport.ClientConstants.*;

import java.util.HashMap;
import java.util.List;

import com.google.common.collect.Maps;
import org.openstack4j.api.dns.v2.ZoneService;
import org.openstack4j.model.dns.v2.Nameserver;
import org.openstack4j.model.dns.v2.Zone;
import org.openstack4j.model.dns.v2.ZoneType;
import org.openstack4j.openstack.dns.v2.domain.DesignateNameserver;
import org.openstack4j.openstack.dns.v2.domain.DesignateZone;

public class ZoneServiceImpl extends BaseDNSServices implements ZoneService {

    @Override
    public Zone get(String zoneId) {
        checkNotNull(zoneId, "The zone id is Null.");
        return get(DesignateZone.class, PATH_ZONES, "/", zoneId).execute();
    }

    @Override
    public Zone delete(String zoneId) {
        checkNotNull(zoneId, "The zone id is Null.");
        return delete(DesignateZone.class, PATH_ZONES, "/", zoneId).execute();
    }

    @Override
    public List<? extends Nameserver> listNameservers(String zoneId) {
        checkNotNull(zoneId, "The zone id is Null.");
        return get(DesignateNameserver.Nameservers.class, PATH_ZONES, "/",zoneId, PATH_NAMESERVERS).execute().getList();
    }

    @Override
    public Zone create(Zone zone) {
        checkNotNull(zone, "The Zone record is Null.");
        checkNotNull(zone.getName(), "The zone record's name properity is Null.");
        checkArgument(checkNameLength(zone.getName()), errorMessageForNameLength(zone.getName()));
        checkArgument(zone.getDescription() != null ? zone.getDescription().length() <= 255 : true, "The description's length is longer than 255");
        checkArgument(zone.getTTL() != null ? zone.getTTL() >= 300 && zone.getTTL() <= 2147483647 : true, "TTL value shold equal or bigger than 300, and equal or less than 2147483647");
        checkArgument(zone.getType() == ZoneType.PRIVATE ? ((DesignateZone)zone).getRouter() != null : true, "The router should not be empty in the private zone");
        return post(DesignateZone.class, uri(PATH_ZONES)).entity(zone).execute();
    }

    @Override
    public Zone create(String name, String email) {
        return create(DesignateZone.builder().name(name).email(email).build());
    }

    private boolean checkNameLength(String name) {
        if (name.endsWith(".")) {
            if (name.length() <= 254) return true;
        } else {
            if (name.length() <= 253) return true;
        }
        return false;
    }

    private String errorMessageForNameLength(String name) {
        if (name.endsWith(".")) {
            if (name.length() > 254) return "The name end with dot, its length should not longer than 254";
        } else {
            if (name.length() > 253) return "The name doesn't end with dot, its length should not longer than 253";
        }
        return "";
    }

    @Override
    public List<? extends Zone> list() {
        return get(DesignateZone.Zones.class, uri(PATH_ZONES)).execute().getList();
    }

    @Override
    public List<? extends Zone> list(String type, String marker, String limit) {
        Invocation<DesignateZone.Zones> invocation = get(DesignateZone.Zones.class, uri(PATH_ZONES));
        invocation.param("type", type);
        invocation.param("marker", marker);
        invocation.param("limit", limit);
        return invocation.execute().getList();
    }

    @Override
    public DesignateZone.Router associateRouter(String zoneId, DesignateZone.Router router) {
        verifyParameters(zoneId, router);
        HashMap<Object, Object> entity = Maps.newHashMap();
        entity.put("router", router);
        return post(DesignateZone.Router.class, uri(PATH_ZONES), "/", zoneId, PATH_ASSOCIATE).entity(entity).execute();
    }

    @Override
    public DesignateZone.Router disassociateRouter(String zoneId, DesignateZone.Router router) {
        verifyParameters(zoneId, router);
        HashMap<Object, Object> entity = Maps.newHashMap();
        entity.put("router", router);
        return post(DesignateZone.Router.class, uri(PATH_ZONES), "/", zoneId, PATH_DISASSOCIATE).entity(entity).execute();
    }

    private void verifyParameters(String zoneId, DesignateZone.Router router) {
        checkNotNull(zoneId, "The zone id is Null.");
        checkNotNull(router, "The router is Null.");
        checkNotNull(router.getId(), "The router's id is Null.");
        checkNotNull(router.getRegion(), "The router's region is Null.");
    }

}
