Feature: Data Driven Testing

Background: 

#* url 'https://api.thecatapi.com/v1/images/search'

* url 'https://api.thecatapi.com/v1/categories'

Scenario:

#Given path 'GET /api/Authors/1'
#And request('name':'hats','id':1)
When method GET
Then status 200 
Then print response
* def valresponse = response.total
Then print 'print-------------' , valresponse

* def jfile = read('DataDriven.json')

Then print 'print json-----', read('DataDriven.json')

#And match valresponse contains { "name":"hats","id":1 }
And match response == jfile
And match response contains [{ "name":"hats","id":1 }, { "name":"space","id":2 }]




