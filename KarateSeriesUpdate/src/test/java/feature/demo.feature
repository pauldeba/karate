Feature: Read the XML file and validate

Background:

Scenario: Read xml file

* def jfile = read('response.xml')

Then print jfile

#And match jfile contains read('response.json')			
	

And match read('response.json').TXLifeRequest.TransRefGUID == '612e5646-5214-11e9-a827-c0a87f790000'	

And match read('response.json').TXLifeRequest.OLifE.SourceInfo.CreationDate == '2019-03-29'

