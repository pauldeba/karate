package com.standard.test;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class Db2Util extends SpringSqlUtil {
    
    protected static Log log = LogFactory.getLog(MsSqlUtil.class);
    private static final String TD_PASS = "DB.password";

    private static final String driverClassName = "com.ibm.as400.access.AS400JDBCDriver";

    public Db2Util(Map<String, Object> config) {
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

        log.info(String.format("init Db2 jdbc template: %s", formattedUrl));
    }

    /** Use this method to debug this Java code */
    //    public static void main(String[] args) {
    //        Map<String, Object> oConfig = new HashMap<>();
    //        oConfig.put("hostname", "cdr400.standard.com");
    //        oConfig.put("port", "446");
    //        oConfig.put("username", "FPOTEST3");
    //        oConfig.put("database", "ASDP04");
    //        Db2Util util = new Db2Util(oConfig);
    //        List<Map<String, Object>> values = util.readRows("select credential from CoreApps_Credentials");
    //        System.out.println(values.toString());
    //    }

    @Override
    public String getPasswordForThisResource() {
        return System.getProperty(TD_PASS);
    }
    
}
