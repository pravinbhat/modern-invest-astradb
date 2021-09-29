package com.datastax.astra.entity;

import java.io.Serializable;
import java.util.UUID;

import com.datastax.oss.driver.api.mapper.annotations.ClusteringColumn;
import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Entity representing Table 
 */
@Entity
@CqlName(ActivityLog.TABLE_NAME)
public class ActivityLog implements Serializable {

    /** Serial. */
    private static final long serialVersionUID = 1L;
    
    /** Constants.*/
    public static final String TABLE_NAME             = "activity_log";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_ACTIVITY_YEAR              = "activity_year";
    public static final String COLUMN_ACTIVITY_ID           = "activity_id";
    public static final String COLUMN_ACTIVITY_TYPE             = "activity_type";
    public static final String COLUMN_FUND_NAME         = "fund_name";
    public static final String COLUMN_AMOUNT         = "amount";
    
    @PartitionKey(0)
    @CqlName(COLUMN_USER_ID)
    @JsonProperty(COLUMN_USER_ID)
    private UUID userId;
    
    @PartitionKey(1)
    @CqlName(COLUMN_ACTIVITY_YEAR)
    @JsonProperty(COLUMN_ACTIVITY_YEAR)
    private String activity_year;
    
    @ClusteringColumn
    @CqlName(COLUMN_ACTIVITY_ID)
    @JsonProperty(COLUMN_ACTIVITY_ID)
    private UUID activityId;
    
    @CqlName(COLUMN_ACTIVITY_TYPE)
    private String activityType;
    
    @CqlName(COLUMN_FUND_NAME)
    private String fundName;
    
    @CqlName(COLUMN_AMOUNT)
    private Double amount;
    
    public ActivityLog() {}

	@Override
	public String toString() {
		return "ActivityLog [userId=" + userId + ", activity_year=" + activity_year + ", activityId=" + activityId
				+ ", activityType=" + activityType + ", fundName=" + fundName + ", amount=" + amount + "]";
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public String getActivity_year() {
		return activity_year;
	}

	public void setActivity_year(String activity_year) {
		this.activity_year = activity_year;
	}

	public UUID getActivityId() {
		return activityId;
	}

	public void setActivityId(UUID activityId) {
		this.activityId = activityId;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
    
}
