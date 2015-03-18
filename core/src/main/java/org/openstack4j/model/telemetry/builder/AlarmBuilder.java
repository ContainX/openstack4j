package org.openstack4j.model.telemetry.builder;

import java.util.List;

import org.openstack4j.common.Buildable.Builder;
import org.openstack4j.model.telemetry.Alarm;
import org.openstack4j.model.telemetry.Alarm.Type;
import org.openstack4j.openstack.telemetry.domain.CeilometerAlarm.CeilometerThresholdRule;

/**
 * An alarm builder
 * 
 * @author Martin Belperchinov
 */

public interface AlarmBuilder extends Builder<AlarmBuilder, Alarm>{

	AlarmBuilder name(String name);
	
	AlarmBuilder okActions(List<String> okActions);
	
	AlarmBuilder alarmActions(List<String> alarmActions);
	
	AlarmBuilder type(Type type);
	
	AlarmBuilder thresholeRule(CeilometerThresholdRule te);
	
	AlarmBuilder repeatActions(boolean repeatActions);
	
	AlarmBuilder description(String description);

}
