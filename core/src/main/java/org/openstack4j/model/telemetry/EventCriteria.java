package org.openstack4j.model.telemetry;

import com.google.common.collect.Lists;
import org.openstack4j.openstack.internal.Parser;

import java.util.Date;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Query options used in retreiving Samples
 * 
 * @author Jeremy Unruh
 */
public class EventCriteria {

    public enum Oper {
        /** Less Than : < */
        LT("lt"),
        /** Greater Than : > */
        GT("gt"),
        /** Less Than Equals : <= */
        LTE("le"),
        /** Greater Than Equals : >= */
        GTE("ge"),
        /** Equals : = */
        EQUALS("eq")
        ;
        private final String queryValue;
        Oper(String queryValue) {
            this.queryValue = queryValue;
        }
        
        public String getQueryValue() {
            return queryValue;
        }
    }
    
    private List<NameOpValue> params = Lists.newArrayList();
    
    public static EventCriteria create() {
        return new EventCriteria();
    }

    /**
     * Matches the given resource identifier
     * @param resourceId the resource id
     * @return SampleCriteria
     */
    public EventCriteria eventType(String eventType) {
        checkNotNull(eventType, "eventType must not be null");
        return add("event_type", Oper.EQUALS, eventType);
    }

    /**
     * Matches the given resource identifier
     * @param resourceId the resource id
     * @return SampleCriteria
     */
    public EventCriteria messageId(String messageId) {
        checkNotNull(messageId, "messageId must not be null");
        return add("message_id", Oper.EQUALS, messageId);
    }
    
    /**
     * Adds a timestamp sample criteria
     * @param operator the operator
     * @param value the date for this timestamp
     * @return SampleCriteria
     */
    public EventCriteria startTimestamp(Oper operator, Date value) {
        checkNotNull(value, "Date must not be null");
        return add("start_timestamp", operator, Parser.toISO8601DateFormat(value));
    }

    /**
     * Adds a timestamp sample criteria
     * @param operator the operator
     * @param value the date for this timestamp
     * @return SampleCriteria
     */
    public EventCriteria endTimestamp(Oper operator, Date value) {
        checkNotNull(value, "Date must not be null");
        return add("end_timestamp", operator, Parser.toISO8601DateFormat(value));
    }
    
    /**
     * Adds an adhoc field criteria
     * @param field the field name (must be the JSON name)
     * @param operator the operator
     * @param value the value
     * @return SampleCriteria
     */
    public EventCriteria add(String field, Oper operator, Number value) {
        checkNotNull(value, "Value must not be null");
        return add(field, operator, value.toString());
    }
    
    public EventCriteria add(String field, Oper operator, String value) {
        checkNotNull(field, "Field must not be null");
        checkNotNull(operator, "Operator must not be null");
        checkNotNull(value, "Value must not be null");

        params.add(new NameOpValue(field, operator, value));
        return this;
    }
    
    /**
     * @return the criteria parameters for this query
     */
    public List<NameOpValue> getCriteriaParams() {
        return params;
    }
    
    public static class NameOpValue {
        private final String field;
        private final Oper operator;
        private String value;
        
        NameOpValue(String field, Oper operator, Comparable<?> value) {
            this.field = field;
            this.operator = operator;
            if (value instanceof Date) 
                this.value = Parser.toISO8601DateFormat(Date.class.cast(value));
            else
                this.value = String.valueOf(value);
        }
        
        public String getField() {
            return field;
        }
        
        public Oper getOperator() {
            return operator;
        }
        
        public String getValue() {
            return value;
        }
    }
}
