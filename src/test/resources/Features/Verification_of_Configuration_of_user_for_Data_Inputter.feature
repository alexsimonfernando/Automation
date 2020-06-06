Feature: To Verify configuration of the User for data inputtter
  Description: 
  PreCondition: 1.User should have admin Access.

  @sanity
  Scenario Outline: To create new user role in admin
    Given Customer opens the application
    When Customer enters email as "<UserName>" and password as "<Password>" and clicked login
    When user clicks on the "User Creation" tab
    When user clicks on the "ADD " button
    When user fill the below data
      | Field      | Value          |
      | User Name  | data.inputter2 |
      | First Name | Test User      |
      | Last Name  | User1          |
      | Email      | test@gmail.com |
    When user selects the role "Admin" from the dropdown
    When user clicks on the "Save" button
    Then verify the "User Added Successfully" pop up message is displayed.
    When user logout of the application.

    Examples: 
      | UserName | Password |
      | admin    | abc      |

  @sanity
  Scenario Outline: To verify the created user
    #Given Customer opens the application
    When Customer enters email as "<UserName>" and password as "<Password>" and clicked login
    Then verify the "Test User User1" text element is displayed.
    When user logout of the application.

    Examples: 
      | UserName       | Password   |
      | data.inputter2 | smplususer |

  @sanity
  Scenario Outline: To delete the created user
    #Given Customer opens the application
    When Customer enters email as "<UserName>" and password as "<Password>" and clicked login
    When user clicks on the "User Creation" tab
    When user "delete" the user "test@gmail.com" from the admin
    Then verify the "Are you sure, you want to delete this item?" pop up message is displayed.
    When user clicks on the "Yes" button
    Then verify the "User Deleted Successfully" pop up message is displayed.
    When user logout of the application.

    Examples: 
      | UserName | Password |
      | admin    | abc      |
