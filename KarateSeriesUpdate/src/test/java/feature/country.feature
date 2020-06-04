Feature: check returned countries by API


	Background: 
		* url 'https://restcountries.eu'
		* header Accept = 'application/json'
	Scenario: get list of all country
		Given path 'rest/v2/all'
		When method get
		Then status 200	
		
		