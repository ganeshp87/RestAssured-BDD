Feature: ComkplexDataGet
  Verify Complex data using REST-assured

  Scenario: Verify GET opertion for Complex data
	    Given I perform GET operation with query parameters for address "/location/"
	    |id|
	    |1|
	    Then I should see the street name as "1st Street" for the "primary" address

  Scenario: Verify GET opertion for Complex data for version2 RestAssuredExtension
	    Given I perform GET operation with query parameters for address version2 RestAssuredExtension "/location/"
	    |id|
	    |1|
	    Then I should see the street name as "1st Street" for the "primary" address
 
 
  Scenario: Verify one author of the post using Builder Pattern
	    Given I perform GET operation for the pojo test "/posts/" for builder pattern
	    |id|
	    |1|
	    Then I should see the author name as for the Builder Pattern test as "Ganesh P"