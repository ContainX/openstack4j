package org.openstack4j.openstack.telemetry.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.openstack4j.model.telemetry.Statistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

/**
 * Computed Statistics for a Query against a Meter
 *
 * @author Jeremy Unruh
 */
public class CeilometerStatistics implements Statistics {
   private static final Logger LOG = LoggerFactory.getLogger(CeilometerStatistics.class);
	private static final long serialVersionUID = 1L;

   private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";
   private static final int DATE_FORMAT_LENGTH = 23;

	private Double avg;

	private Integer count;

	private Double duration;

	@JsonProperty("duration_start")
   private String durationStartStr;

   @JsonProperty("duration_end")
   private String durationEndStr;

	private Date durationStart;
	
	private Date durationEnd;

	private Double max;

	private Double min;

	private Integer period;

   @JsonProperty("period_start")
	private Date periodStart;

   @JsonProperty("period_end")
	private Date periodEnd;

	private Double sum;

	private String unit;

   @JsonProperty("groupby")
   private Map<String, Object> groupBy;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Double getAvg() {
		return avg;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getCount() {
		return count;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Double getDuration() {
		return duration;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date getDurationStart() {
	   if(durationStart == null){
         durationStart = parseDate(durationStartStr);
	   }
		return durationStart;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date getDurationEnd() {
      if (durationEnd == null) {
         durationEnd = parseDate(durationEndStr);
      }
      return durationEnd;
	}

   /**
    * {@inheritDoc}
    */
	@Override
	public Double getMax() {
		return max;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Double getMin() {
		return min;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Double getSum() {
		return sum;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getPeriod() {
		return period;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date getPeriodStart() {
      return periodStart;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date getPeriodEnd() {
		return periodEnd;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getUnit() {
		return unit;
	}

  /**
   * {@inheritDoc}
   */
  @Override
   public Map<String, Object> getGroupBy() {
      return groupBy;
  }
  
  private Date parseDate(String date) {
    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
    try {
      return sdf.parse(getTrimmedDate(date));
    } catch (ParseException e) {
      LOG.warn("Error while parsing date", e);
    }
    return null;
  }

   private String getTrimmedDate(String date) {
      // convert "2017-06-19T10:00:00.395000"
      if (date.length() > DATE_FORMAT_LENGTH) {
         // to "2017-06-19T10:00:00.395"
         return date.substring(0, DATE_FORMAT_LENGTH - 1);
      }
      return date;
   }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).omitNullValues()
				    .add("avg", avg).add("count", count).add("duration", duration)
				    .add("durationStart", getDurationStart()).add("durationEnd", getDurationEnd())
				    .add("min", min).add("max", max).add("sum", sum).add("period", period)
				    .add("periodStart", periodStart).add("periodEnd", periodEnd).add("unit", unit)
				    .add("groupBy", groupBy)
				    .toString();
	}

}
