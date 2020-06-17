package com.standard.test;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

public abstract class SpringSqlUtil {

    protected JdbcTemplate jdbc;

    public Object readValue(String query) {
        return jdbc.queryForObject(query, Object.class);
    }    
 
    public Map<String, Object> readRow(String query) {
        return jdbc.queryForMap(query);
    }
 
    public List<Map<String, Object>> readRows(String query) {
        return jdbc.queryForList(query);
    }

    public abstract String getPasswordForThisResource();
}
