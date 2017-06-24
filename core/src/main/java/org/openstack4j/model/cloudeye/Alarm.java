package org.openstack4j.model.cloudeye;

import org.openstack4j.model.ModelEntity;

import java.util.Date;
import java.util.List;


/**
 * Created by coa.ke on 6/24/17.
 */
public interface Alarm extends ModelEntity {
    String getAlarmName();
    String getAlarmDescription();
    Metric getMetric();
    Condition getCondition();
    List<? extends AlarmAction> getAlarmActions();
    List<? extends AlarmAction> getInsufficientdataActions();
    List<? extends AlarmAction> getOkActions();
    Boolean getAlarmEnabled();
    Boolean getAlarmActionEnabled();
    String getAlarmId();
    Date getUpdateTime();
    AlarmState getAlarmState();

}
