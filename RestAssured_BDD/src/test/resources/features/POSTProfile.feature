Feature: PostProfile
  Test POST operation using REST-assured library
  
   Scenario: Verify Post operation for Profile
    	Given I Perform POST operation for "/posts/{profileNo}/profile" with body
    	|name     | profile |
    	|Ganesh Palanisamy | 2       |
    	Then  I should see the name as "Ganesh Palanisamy"