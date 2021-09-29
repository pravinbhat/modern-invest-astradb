package com.datastax.astra.dao;

import java.util.UUID;

import com.datastax.astra.entity.ActivityLog;
import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Insert;
import com.datastax.oss.driver.api.mapper.annotations.Select;

@Dao
public interface ActivityLogDao {

    
    /**
     * Search for activity
     */
    @Select(customWhereClause = "user_id= :userId AND activity_year= :activityYear AND activity_id= :activityId")
    PagingIterable<ActivityLog> getActivityLog(
            UUID userId, String activityYear, UUID activityId);
    
    /**
     * Upsert an activity.
     */
    @Insert
    boolean upsertActivityLog(ActivityLog activityLog);
}
