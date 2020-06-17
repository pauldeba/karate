
Feature: Example Oracle query test

  Background:
    * def tdmConfig = global.db
    * def getOracleCredential =
      """
      function(arg) {
        var MsSqlUtil = Java.type('com.standard.test.MsSqlUtil');
        var tdm = new MsSqlUtil(tdmConfig);
        var sqlQuery = "select credential from CoreApps_Credentials where identifier = '" + arg + "'";
        return tdm.readValue(sqlQuery);  
      }
      """
    * def oraConfig = global.oracle
    * def getOracleTableCount =
      """
      function() {
        var secret = getOracleCredential(global.oracle.username);
        var OracleSqlUtil = Java.type('com.standard.test.OracleSqlUtil');
        var odb = new OracleSqlUtil(oraConfig, secret);
        return odb.tables(); 
      }
      """

  @reg
  Scenario: Get count of CIS Table List
    * def tables = call getOracleTableCount
    * karate.log("Table count: " + tables.size() + ", Names: " + tables)
    * assert tables.size() > 15

  