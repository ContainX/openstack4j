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
package org.openstack4j.openstack.trove.domain;

import java.util.List;

import org.openstack4j.model.ModelEntity;
import org.openstack4j.openstack.common.ListResult;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Model represent attributes of Trove Database instance
 *
 * @author QianBiao.NG
 * @date   2017-07-31 11:12:39
 */
@Getter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class TroveDatabase implements ModelEntity {

	private static final long serialVersionUID = 4318036798508606114L;

	private String name;
	@JsonProperty("character_set")
	private String dbCharacterSet;
	@JsonProperty("collate")
	private String dbCollation;

	public static class Databases extends ListResult<TroveDatabase> {

		private static final long serialVersionUID = 1L;

		@JsonProperty("databases")
		private List<TroveDatabase> troveDatabaseList;

		public List<TroveDatabase> getTroveDatabaseList() {
			return troveDatabaseList;
		}

		public void setTroveDatabaseList(List<TroveDatabase> troveDatabaseList) {
			this.troveDatabaseList = troveDatabaseList;
		}

		@Override
		protected List<TroveDatabase> value() {
			return troveDatabaseList;
		}
	}

}
