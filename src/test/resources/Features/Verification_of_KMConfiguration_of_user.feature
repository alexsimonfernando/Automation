Feature: To Verify the KM Creation and deletion
  Description: 
  PreCondition: 1.Data inputter should have configured to farmergroup product origin
  2.Campaign should be currently running inorder to sumbit the data

  @sanity
  Scenario Outline: To verify creation of new KM
    Given Customer opens the application
    When Customer enters email as "<UserName>" and password as "<Password>"
    When user clicks on the "KM Configuration" tab
    When user clicks on the "Add" button
    When user fill the below data
      | Field           | Value          |
      | Enter KM        | KM-502         |
      | KM Nomenclature | KM Description |
      | Range Low       |            100 |
      | Range High      |            120 |
      | KM UOM          | Number         |
    When user selects the KmType "Derived" and famergroup type "Farmer Group" from the dropdown
    When user clicks on the "Save" button
    Then verify the "Km Added Successfully" pop up message is displayed.
    When user logout of the application.

    #Are you sure to remove the selected KPIs, Proceed,Cancel
    Examples: 
      | UserName | Password |
      | admin    | abc      |

  @sanity
  Scenario Outline: To verify removal of created KM
    Given Customer opens the application
    When Customer enters email as "<UserName>" and password as "<Password>"
    When user clicks on the "KM Configuration" tab
    When user selects the KM "KM-502" for deleting
    When user clicks on the "Remove" button
    Then verify the "Are you sure to remove the selected KPIs" pop up message is displayed.
    When user clicks on the "Proceed" button
    Then verify the "Successfully removed" pop up message is displayed.
    When user logout of the application.
    #Are you sure to remove the selected KPIs, Proceed,Cancel
    Examples: 
      | UserName | Password |
      | admin    | abc      |
