package com.snack.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andOIdIsNull() {
            addCriterion("o_id is null");
            return (Criteria) this;
        }

        public Criteria andOIdIsNotNull() {
            addCriterion("o_id is not null");
            return (Criteria) this;
        }

        public Criteria andOIdEqualTo(String value) {
            addCriterion("o_id =", value, "oId");
            return (Criteria) this;
        }

        public Criteria andOIdNotEqualTo(String value) {
            addCriterion("o_id <>", value, "oId");
            return (Criteria) this;
        }

        public Criteria andOIdGreaterThan(String value) {
            addCriterion("o_id >", value, "oId");
            return (Criteria) this;
        }

        public Criteria andOIdGreaterThanOrEqualTo(String value) {
            addCriterion("o_id >=", value, "oId");
            return (Criteria) this;
        }

        public Criteria andOIdLessThan(String value) {
            addCriterion("o_id <", value, "oId");
            return (Criteria) this;
        }

        public Criteria andOIdLessThanOrEqualTo(String value) {
            addCriterion("o_id <=", value, "oId");
            return (Criteria) this;
        }

        public Criteria andOIdLike(String value) {
            addCriterion("o_id like", value, "oId");
            return (Criteria) this;
        }

        public Criteria andOIdNotLike(String value) {
            addCriterion("o_id not like", value, "oId");
            return (Criteria) this;
        }

        public Criteria andOIdIn(List<String> values) {
            addCriterion("o_id in", values, "oId");
            return (Criteria) this;
        }

        public Criteria andOIdNotIn(List<String> values) {
            addCriterion("o_id not in", values, "oId");
            return (Criteria) this;
        }

        public Criteria andOIdBetween(String value1, String value2) {
            addCriterion("o_id between", value1, value2, "oId");
            return (Criteria) this;
        }

        public Criteria andOIdNotBetween(String value1, String value2) {
            addCriterion("o_id not between", value1, value2, "oId");
            return (Criteria) this;
        }

        public Criteria andOStartTimeIsNull() {
            addCriterion("o_start_time is null");
            return (Criteria) this;
        }

        public Criteria andOStartTimeIsNotNull() {
            addCriterion("o_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andOStartTimeEqualTo(Date value) {
            addCriterion("o_start_time =", value, "oStartTime");
            return (Criteria) this;
        }

        public Criteria andOStartTimeNotEqualTo(Date value) {
            addCriterion("o_start_time <>", value, "oStartTime");
            return (Criteria) this;
        }

        public Criteria andOStartTimeGreaterThan(Date value) {
            addCriterion("o_start_time >", value, "oStartTime");
            return (Criteria) this;
        }

        public Criteria andOStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("o_start_time >=", value, "oStartTime");
            return (Criteria) this;
        }

        public Criteria andOStartTimeLessThan(Date value) {
            addCriterion("o_start_time <", value, "oStartTime");
            return (Criteria) this;
        }

        public Criteria andOStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("o_start_time <=", value, "oStartTime");
            return (Criteria) this;
        }

        public Criteria andOStartTimeIn(List<Date> values) {
            addCriterion("o_start_time in", values, "oStartTime");
            return (Criteria) this;
        }

        public Criteria andOStartTimeNotIn(List<Date> values) {
            addCriterion("o_start_time not in", values, "oStartTime");
            return (Criteria) this;
        }

        public Criteria andOStartTimeBetween(Date value1, Date value2) {
            addCriterion("o_start_time between", value1, value2, "oStartTime");
            return (Criteria) this;
        }

        public Criteria andOStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("o_start_time not between", value1, value2, "oStartTime");
            return (Criteria) this;
        }

        public Criteria andOEndTimeIsNull() {
            addCriterion("o_end_time is null");
            return (Criteria) this;
        }

        public Criteria andOEndTimeIsNotNull() {
            addCriterion("o_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andOEndTimeEqualTo(Date value) {
            addCriterion("o_end_time =", value, "oEndTime");
            return (Criteria) this;
        }

        public Criteria andOEndTimeNotEqualTo(Date value) {
            addCriterion("o_end_time <>", value, "oEndTime");
            return (Criteria) this;
        }

        public Criteria andOEndTimeGreaterThan(Date value) {
            addCriterion("o_end_time >", value, "oEndTime");
            return (Criteria) this;
        }

        public Criteria andOEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("o_end_time >=", value, "oEndTime");
            return (Criteria) this;
        }

        public Criteria andOEndTimeLessThan(Date value) {
            addCriterion("o_end_time <", value, "oEndTime");
            return (Criteria) this;
        }

        public Criteria andOEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("o_end_time <=", value, "oEndTime");
            return (Criteria) this;
        }

        public Criteria andOEndTimeIn(List<Date> values) {
            addCriterion("o_end_time in", values, "oEndTime");
            return (Criteria) this;
        }

        public Criteria andOEndTimeNotIn(List<Date> values) {
            addCriterion("o_end_time not in", values, "oEndTime");
            return (Criteria) this;
        }

        public Criteria andOEndTimeBetween(Date value1, Date value2) {
            addCriterion("o_end_time between", value1, value2, "oEndTime");
            return (Criteria) this;
        }

        public Criteria andOEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("o_end_time not between", value1, value2, "oEndTime");
            return (Criteria) this;
        }

        public Criteria andUIdIsNull() {
            addCriterion("u_id is null");
            return (Criteria) this;
        }

        public Criteria andUIdIsNotNull() {
            addCriterion("u_id is not null");
            return (Criteria) this;
        }

        public Criteria andUIdEqualTo(String value) {
            addCriterion("u_id =", value, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdNotEqualTo(String value) {
            addCriterion("u_id <>", value, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdGreaterThan(String value) {
            addCriterion("u_id >", value, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdGreaterThanOrEqualTo(String value) {
            addCriterion("u_id >=", value, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdLessThan(String value) {
            addCriterion("u_id <", value, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdLessThanOrEqualTo(String value) {
            addCriterion("u_id <=", value, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdLike(String value) {
            addCriterion("u_id like", value, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdNotLike(String value) {
            addCriterion("u_id not like", value, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdIn(List<String> values) {
            addCriterion("u_id in", values, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdNotIn(List<String> values) {
            addCriterion("u_id not in", values, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdBetween(String value1, String value2) {
            addCriterion("u_id between", value1, value2, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdNotBetween(String value1, String value2) {
            addCriterion("u_id not between", value1, value2, "uId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}