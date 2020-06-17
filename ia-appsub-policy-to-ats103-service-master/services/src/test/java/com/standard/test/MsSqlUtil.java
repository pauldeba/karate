package com.standard.test;

import java.util.Map;
import java.util.Objects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Handler for MSSQL SQL queries.
 */
public class MsSqlUtil extends SpringSqlUtil {
    
    protected static Log log = LogFactory.getLog(MsSqlUtil.class);
    private static final String TD_PASS = "DB.password";

    private static final String driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public MsSqlUtil(Map<String, Object> config) {
        final String hostname = (String)config.get("hostname");
        final String port = (String)config.get("port");
        final String database = (String)config.get("database");
        final String username = (String)config.get("username");

        final String url = "jdbc:sqlserver://%s:%s;databaseName=%s";
        final String formattedUrl = String.format(url, hostname, port, database);

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(formattedUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(getPasswordForThisResource());
        
        jdbc = new JdbcTemplate(dataSource);

        log.info(String.format("init MsSql jdbc template: %s", formattedUrl));
    }

    /** Use this method to debug this Java code */
    //    public static void main(String[] args) {
    //        Map<String, Object> oConfig = new HashMap<>();
    //        oConfig.put("hostname", "HLBWMSQLV084.corp.standard.com");
    //        oConfig.put("port", "60084");
    //        oConfig.put("username", "test_tdm_dev");
    //        oConfig.put("database", "TDINT");
    //        MsSqlUtil msSqlUtil = new MsSqlUtil(oConfig);
    //        List<Map<String, Object>> values = msSqlUtil.readRows("select credential from CoreApps_Credentials");
    //        System.out.println(values.toString());
    //    }

    @Override
    public String getPasswordForThisResource() {
        String tdPassword = System.getProperty(TD_PASS);
        Objects.requireNonNull(tdPassword, "The DB.password arg is required to be passed to this test suite.");
        System.setProperty("TD_PASS", tdPassword); // could be necessary if you use our in-house TDM Java lib
        return tdPassword;
    }
    
}
