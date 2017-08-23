/*******************************************************************************
 * 	Copyright 2017 HuaWei Tld                                     
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
package com.huawei.openstack4j.api.database;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.huawei.openstack4j.api.AbstractTest;
import com.huawei.openstack4j.openstack.database.constants.StatementType;
import com.huawei.openstack4j.openstack.database.domain.DatabaseErrorLog;
import com.huawei.openstack4j.openstack.database.domain.DatabaseSlowLog;
import com.huawei.openstack4j.openstack.database.options.ErrorLogListOptions;

import okhttp3.mockwebserver.RecordedRequest;

@Test(suiteName = "Database/datastore", enabled = true)
public class DatabaseLogTest extends AbstractTest {

	@Test
	public void testListErrorLog() throws IOException, InterruptedException {
		respondWith("/database/list_errorlog_response.json");

		Date now = new Date(1502531254806L);
		Date tenDaysAgo = new Date(now.getTime() - 10 * 24 * 3600 * 1000);
		ErrorLogListOptions options = ErrorLogListOptions.create().instanceId("instance-id").startDate(tenDaysAgo)
				.endDate(now).curPage(1).perPage(20);
		List<DatabaseErrorLog> list = osv3().database().logs().listErrorLogs(options);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/rds/v1/project-id/instances/instance-id/errorlog?"
				+ "startDate=2017-08-02%2B17%3A47&curPage=1&endDate=2017-08-12%2B17%3A47&perPage=20");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(list.size(), 17);

		DatabaseErrorLog log = list.get(0);
		Assert.assertEquals(log.getDatetime().getTime(), 1502524910000L);
		Assert.assertEquals(log.getContent(),
				"[Warning] file ./mysql-bin.000002 was not purged because it was being readby thread number 15829");
	}

	@Test
	public void testListSlowLog() throws IOException, InterruptedException {
		respondWith("/database/list_slowlog_response.json");

		List<DatabaseSlowLog> list = osv3().database().logs().listSlowLogs("instance-id", StatementType.SELECT, 30);

		RecordedRequest request = server.takeRequest();
		Assert.assertEquals(request.getPath(), "/rds/v1/project-id/instances/instance-id/slowlog?sftype=SELECT&top=30");
		Assert.assertEquals(request.getMethod(), "GET");

		Assert.assertEquals(list.size(), 2);

        	
		DatabaseSlowLog log = list.get(0);
		Assert.assertEquals(log.getCount(), "409  (99.76%)");
		Assert.assertEquals(log.getTime(), 1.29D);
		Assert.assertEquals(log.getLockTime(), 0D);
		Assert.assertEquals(log.getRowsSent().intValue(), 0);
		Assert.assertEquals(log.getRowsExamined().intValue(), 0);
		Assert.assertEquals(log.getDatabase(), "");
		Assert.assertEquals(log.getUsers(), "\trdsBackup@localhost  : 100.00% (409) of query, 99.76% (409) of all users");
		Assert.assertEquals(log.getQuerySample(), "flush logs;");
	}

	@Override
	protected Service service() {
		return Service.DATABASE;
	}

}
