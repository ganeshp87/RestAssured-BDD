Feature: DeletePosts
  Test the delete operation

  @smoke
  Scenario: Verify DELETE operation after POST
    Given I ensure to Perform POST operation for "/posts" with body as
      | id | title              | author            |
      | 8  | API Testing | ExecuteAutomation |
    And  I Perform DELETE operation for "/posts/{postid}/"
      | postid |
      | 8      |
    And I perform GET operation with path parameter for "/posts/{postid}"
      | postid |
      | 8     |
    Then I "should not" see the body with title as "API Testing"
