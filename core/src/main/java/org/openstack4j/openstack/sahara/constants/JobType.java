package org.openstack4j.openstack.sahara.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 *
 * @author QianBiao.NG
 * @date   2017-07-12 15:33:40
 */
public enum JobType {

	MapReduce(1), Spark(2), Hive(3), Hql(4), DistCp(5), SparkScript(6), SparkSql(7),;

	Integer value;

	JobType(Integer value) {
		this.value = value;
	}

	@JsonValue
	public Integer value() {
		return value;
	}

	@JsonCreator
	public static JobType value(Integer v) {
		JobType[] values = JobType.values();
		for (JobType jobType : values) {
			if(jobType.value.equals(v)) {
				return jobType;
			}
		}
		return null;
	}
}