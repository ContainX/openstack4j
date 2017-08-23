/*******************************************************************************
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
package com.huawei.openstack4j.model.compute;

/**
 * Basic Actions against a Live Server
 * 
 * @author Jeremy Unruh
 */
public enum Action {
	/** Pause the server */
	PAUSE, 
	/** UnPause the paused server */
	UNPAUSE, 
	/** Stop the server */
	STOP, 
	/** Start the server */
	START, 
	/** Lock the server */
	LOCK, 
	/** Unlock a locked server */
	UNLOCK, 
	/** Suspend a server */
	SUSPEND, 
	/** Resume a suspended server */
	RESUME, 
	/** Rescue the server */
	RESCUE, 
	/** Unrescue the server */
	UNRESCUE, 
	/** Shelve the server */
	SHELVE, 
	/** Remove a shelved instance from the compute node */
	SHELVE_OFFLOAD, 
	/** Unshelve the server */
	UNSHELVE,
 	/** Force delete the server */
 	FORCEDELETE
}
