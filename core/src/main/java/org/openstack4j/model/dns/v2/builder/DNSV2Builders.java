package org.openstack4j.model.dns.v2.builder;


/**
 * The Designate V2 builders
 */
public interface DNSV2Builders {

    /**
     * The builder to create a Zone.
     *
     * @return the zone builder
     */
    ZoneBuilder zone();

    /**
     * The builder to create a Recordset.
     *
     * @return the recordset builder
     */
    RecordsetBuilder recordset();

}
