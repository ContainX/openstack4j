package org.openstack4j.model.telemetry;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Date;
import java.util.List;

import org.openstack4j.openstack.internal.Parser;

import com.google.common.collect.Lists;

/**
 * Query options used in retreiving Samples
 * 
 * @author Jeremy Unruh
 */
public class SampleCriteria {

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
        private Oper(String queryValue) {
            this.queryValue = queryValue;
        }
        
        public String getQueryValue() {
            return queryValue;
        }
    }
    
    private List<NameOpValue> params = Lists.newArrayList();
    
    public static SampleCriteria create() {
        return new SampleCriteria();
    }
    
    /**
     * Adds a timestamp sample criteria
     * @param operator the operator
     * @param value the date for this timestamp
     * @return SampleCriteria
     */
    public SampleCriteria timestamp(Oper operator, Date value) {
        checkNotNull(value, "Date must not be null");
        return add("timestamp", operator, Parser.toISO8601DateFormat(value));
    }
    
    /**
     * Adds a timestamp sample criteria
     * @param operator the operator
     * @param value the date for this timestamp
     * @return SampleCriteria
     */
    public SampleCriteria timestamp(Oper operator, long value) {
        checkNotNull(value, "Date must not be null");
        return add("timestamp", operator, Parser.toISO8601DateFormat(new Date(value)));
    }
    
    /**
     * Matches the given resource identifier
     * @param resourceId the resource id
     * @return SampleCriteria
     */
    public SampleCriteria resource(String resourceId) {
        checkNotNull(resourceId, "resourceId must not be null");
        return add("resource_id", Oper.EQUALS, resourceId);
    }
    
    /**
     * Adds an adhoc field criteria
     * @param field the field name (must be the JSON name)
     * @param operator the operator
     * @param value the value
     * @return SampleCriteria
     */
    public SampleCriteria add(String field, Oper operator, Number value) {
        checkNotNull(value, "Value must not be null");
        return add(field, operator, value.toString());
    }
    
    public SampleCriteria add(String field, Oper operator, String value) {
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
