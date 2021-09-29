package com.datastax.astra.dao;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.DaoKeyspace;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;

/**
 * Wrapper for all {@link Dao} generated by DataStax Driver.
 */
@Mapper
public interface ActivityLogMapper {

    @DaoFactory
    ActivityLogDao activityLogDao(@DaoKeyspace CqlIdentifier keyspace);
    
}
