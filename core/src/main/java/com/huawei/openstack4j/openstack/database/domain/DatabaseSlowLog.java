/*******************************************************************************
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
package com.huawei.openstack4j.openstack.database.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huawei.openstack4j.model.ModelEntity;
import com.huawei.openstack4j.openstack.common.ListResult;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Model represent attributes of Database Slow log
 *
 * @author QianBiao.NG
 * @date   2017-07-31 11:12:39
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class DatabaseSlowLog implements ModelEntity {

	private static final long serialVersionUID = 5294355671374520846L;

	/**
	 * query executed times
	 */
	String count;

	/**
	 * average execution duration
	 */
	Double time;

	/**
	 * the average waiting time before locking
	 */
	Double lockTime;

	/**
	 * average number of rows contained in a result
	 */
	Integer rowsSent;
	
	/**
	 * average number of scanned rows
	 */
	Long rowsExamined;
	
	/**
	 * database which the slow query log belongs to
	 */
	String database;
	
	/**
	 * query user
	 */
	String users;
	
	/**
	 * query execution syntax
	 */
	String querySample;
	
	public static class SlowLogs extends ListResult<DatabaseSlowLog> {
		private static final long serialVersionUID = 7666104777418585874L;
		
		@JsonProperty("slowLogList")
		List<DatabaseSlowLog> slowLogs;

		@Override
		protected List<DatabaseSlowLog> value() {
			return slowLogs;
		}

	}

}
