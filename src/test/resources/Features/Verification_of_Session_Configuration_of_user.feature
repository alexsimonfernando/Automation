Feature: To Verify adding or removing the session in admin
  Description: 
  PreCondition: 1.Data inputter should have configured to farmergroup product origin
  2.Campaign should be currently running inorder to sumbit the data

  
  Scenario Outline: To verify the adding of session Configuration.
    Given Customer opens the application
    When Customer enters email as "<UserName>" and password as "<Password>"
    When user clicks on the "Session Configuration" tab
    When user enters the session details "<data>" of datainputter,product,origin,start and end date.
    When user clicks on the "Add" button
    Then verify the "Time Configured for the user data.inputter is set Successfully" pop up message is displayed.
    When user logout of the application.

    Examples: 
      | UserName | Password | data                                            |
      | admin    | abc      | data.inputter,Coffee,Gabon,28/1/2020,30/1/2020 |

  
  Scenario Outline: To verify the adding of session Configuration.
    Given Customer opens the application
    When Customer enters email as "<UserName>" and password as "<Password>"
    When user clicks on the "Session Configuration" tab
    When user "delete" the session of the combination "<data>".
    Then verify the "Are you sure to delete this item?" pop up message is displayed.
    When user clicks on the "Proceed" tab
    Then verify the "Successfully deleted" pop up message is displayed.
    When user logout of the application.

    Examples: 
      | UserName | Password | data                                            |
      | admin    | abc      | data.inputter,Coffee,Gabon,28/1/2020,30/1/2020 |
