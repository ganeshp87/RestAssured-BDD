Feature: GETPosts
  Verify different GET operations using REST-assured

  Scenario: Verify one author of the post
	    Given I perform GET operation for "/posts"
	    Then I should see the author name as "Ganesh P"
	    Then I should see the title as "Restassured with BDD"
    
    Scenario: Verify collection of authors in the post
	    Given I perform GET operation for "/posts"
	    Then I should see the author names
    
    Scenario: Verify Parameter of Get
	    Given I perform GET operation for "/posts"
	    Then I should see verify GET Parameter
    
    Scenario: Verify Parameter of Get
	    Given I perform GET operation for "/posts"
	    Then I should see verify GET Parameter with Query Param
 
   Scenario: Verify one author of the post using Pojo class
	    Given I perform GET operation for the pojo test "/posts/1"
	    Then I should see the author name as for the pojo test as "Ganesh P"
	    
   Scenario: Verify one author of the post using Pojo class for json schema validation
	    Given I perform GET operation for the pojo test "/posts/1"
	    Then I should see the author name as "Ganesh P" with json schema validation
	
 
# crtrl+/ for commenting and un-commenting 
# @smoke
#  Scenario: Verify GET operation with bearer authentication token for json schema validation
#    Given I perform authentication operation for "/auth/login" with body
#      | email             | password |
#      | ganeshp@gmail.com | haha123  |
#    Given I perform GET operation for "/posts/1"
#    Then I should see the author name as "Ganesh P" with json validation
 