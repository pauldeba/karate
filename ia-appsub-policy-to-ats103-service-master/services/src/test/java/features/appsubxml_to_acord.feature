
Feature: AppSubXML to ACORD transformation

  Test the ransformation of the message into an ACORD 103 new application submission transaction. It invokes DB2 
  stored procedures within the ID3 application to gather more information necessary to generate the new policy. 
  Such information include the assigned policy id, product short name (mnemonic plan code), producer id and 
  hierarchy. Some data validation and edits are also performed to increase the likelihood of a successful 
  policy shell creation in ID3. The validation process can result in generation of warning and error messages. 
  These messages are emailed to a business email DL for research and handling.

  Background:
    * def tdmConfig = global.db

  @bvt @reg
  Scenario: AppSubXML converstion to ACORD transaction
    * print 'implement step to check audit log for this transformation?'

