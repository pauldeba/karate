package com.standard.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class OracleSqlUtil extends SpringSqlUtil {
    
    protected static Log log = LogFactory.getLog(OracleSqlUtil.class);

    private static final String driverClassName = "oracle.jdbc.driver.OracleDriver";

    private final Map<String, Object> oracleConfig;

    private final String ORACLE_PASSWORD;

    public OracleSqlUtil(final Map<String, Object> oConfig, final String password) {
        oracleConfig = oConfig;
        ORACLE_PASSWORD = password;

        final String hostname = (String) oracleConfig.get("hostname");
        final String port = (String) oracleConfig.get("port");
        final String username = (String) oracleConfig.get("username");
        final String sid = (String) oracleConfig.get("sid");
        final String sidUrl = "jdbc:oracle:thin:@%s:%s:%s";
        final String formattedUrl = String.format(sidUrl, hostname, port, sid);

        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(formattedUrl); // includes host, port, and sid
        dataSource.setUsername(username);
        dataSource.setPassword(getPasswordForThisResource());

        jdbc = new JdbcTemplate(dataSource);

        log.info(String.format("init Oracle jdbc template: %s", formattedUrl));
    }

    /**
     * Example query to show that querying Oracle is possible.
     * @return a json object of table names
     */
    public List<String> tables() {
        String prefix = "CIS";
        String query = "SELECT TABLE_NAME FROM all_tables WHERE OWNER = '%s' AND TABLE_NAME LIKE '%s_";
        String formattedQuery = String.format(query, prefix, prefix) + "%'";
        List<String> results = new ArrayList<>();
        List<Map<String, Object>> items = readRows(formattedQuery);
        results = items.stream()
            .map(m -> (String)m.get("TABLE_NAME"))
            .collect(Collectors.toList());
        return results;
    }

//    /** Use this method to debug this Java code */
//    public static void main(String[] args) {
//        Map<String, Object> oConfig = new HashMap<>();
//        oConfig.put("hostname", "seq1-ds.standard.com");
//        oConfig.put("port", "1521");
//        oConfig.put("username", "EBERSTMNTCISSVC");
//        oConfig.put("sid", "gtxi");
//        System.setProperty("ORACLE_PASSWORD", "");
//        OracleSqlUtil oracleSqlUtil = new OracleSqlUtil(oConfig, "W1nt3r_vil81");
//        List<String> tables = oracleSqlUtil.tables();
//    }

    @Override
    public String getPasswordForThisResource() {
        if (Objects.isNull(ORACLE_PASSWORD)) {
            return System.getProperty("ORACLE_PASSWORD");
        } else {
            return ORACLE_PASSWORD;
        }
    }

}
