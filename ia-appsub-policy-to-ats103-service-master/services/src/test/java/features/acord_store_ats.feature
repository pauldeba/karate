
Feature: ACORD 103 transaction stored in ATS

  Test the ACORD 103 transaction transmitted to the ATS Web service which ultimately creates the new 
  policy within ID3. A response is sent back and is examined by the flow. If other than a “Success” 
  were received, an email is sent to the business DL to notify them of any warning or error events.

  Background:
    * def tdmConfig = global.db

  @bvt @reg
  Scenario: ACORD 103 transaction stored in ATS
    * print 'implement step to check audit log for this transformation?'

