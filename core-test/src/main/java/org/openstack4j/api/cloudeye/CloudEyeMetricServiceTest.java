/*******************************************************************************
 *  Copyright 2017 HuaWei Tld
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
package org.openstack4j.api.cloudeye;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.cloudeye.Metric;
import org.openstack4j.model.cloudeye.OrderType;
import org.openstack4j.openstack.cloudeye.internal.MetricFilterOptions;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * Tests the CloudEye API version Metric Service
 */
@Test(groups = "Cloudeye", suiteName = "Cloudeye/Metrics")
public class CloudEyeMetricServiceTest extends AbstractTest {

    private static final String JSON_METRICS = "/cloudeye/list_metrics.json";
    private static final String JSON_METRICS_WITH_FILTER = "/cloudeye/list_metrics_with_filter.json";
    private static final String JSON_METRICS_FAVORITE = "/cloudeye/list_metrics_favorite.json";

    @Override
    protected Service service() {
        return Service.CLOUD_EYE;
    }

    public void metricsGetTest() throws Exception {
        respondWith(JSON_METRICS);
        List<? extends Metric> metrics = osv3().cloudEye().metrics().getList();
        assertNotNull(metrics);
        assertEquals(metrics.size(), 3);
        assertEquals(metrics.get(0).getMetricName(), "mem_util");
    }

    public void metricsGetWithFilterTest() throws Exception {
        respondWith(JSON_METRICS_WITH_FILTER);
        MetricFilterOptions config = MetricFilterOptions.create();
        MetricFilterOptions options = config.dim(new String[]{"instance_id,5b4c1602-fb6d-4f1e-87a8-dcf21d9654ba"});
        options.limit(50);
        options.order(OrderType.ASC);
        options.namespace("SYS.ECS");
        List<? extends Metric> metrics = osv3().cloudEye().metrics().getList(options);
        assertNotNull(metrics);
        assertEquals(metrics.size(), 1);
        assertEquals(metrics.get(0).getMetricName(), "network_outgoing_bytes_aggregate_rate");
    }

    public void favoriteMetricsGetTest() throws Exception {
        respondWith(JSON_METRICS_FAVORITE);
        List<? extends Metric> metrics = osv3().cloudEye().metrics().getFavoriteList();
        assertNotNull(metrics);
        assertEquals(metrics.size(), 2);
        assertEquals(metrics.get(1).getMetricName(), "network_outgoing_bytes_aggregate_rate");
    }

}
