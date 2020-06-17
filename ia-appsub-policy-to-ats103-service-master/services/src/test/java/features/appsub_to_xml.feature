
Feature: AppSub to XML transformation

  With the “driver” MQ as its input node and performs an MQGet by correlation id from the other MQ. Specific fields 
  are extracted from each record type to form an appsub XML message.

  Background:
    * def tdmConfig = global.db

  @bvt @reg
  Scenario: AppSub json converted to XML
    * print 'implement step to check audit log for this transformation?'

