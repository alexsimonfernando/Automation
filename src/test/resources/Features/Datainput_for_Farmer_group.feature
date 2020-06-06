Feature: To Verify the datasubmission for farmergroup as datainputter
  Description: 
  PreCondition: 1.Data inputter should have configured to farmergroup product origin
  2.Campaign should be currently running inorder to sumbit the data

  @regression
  Scenario Outline: To Verify the datasubmission for farmergroup as datainputter
    Given Customer opens the application
    When Customer enters email as "<UserName>" and password as "<Password>"
    When user selects the product "Coffee".
    When user selects the origin "Colombia".
    When user enters the below data in column "1"
      | Field              | Value   |
      | Farmer Group       | Testing |
      | Baseline Data Year |    2018 |
      | Current Data Year  |    2019 |
    When user enters the below data in column "5"
      | Field | Value |
      | KM-47 |   100 |
      | KM-48 |   100 |
      | KM-50 |   100 |
      | KM-58 |   100 |
      | KM-71 |   100 |
      | KM-72 |   100 |
      | KM-74 |   100 |
      | KM-83 |   100 |
      | KM-86 |   100 |
      | KM-88 |   100 |
      | KM-92 |   100 |
      | KM-96 |   100 |
    When user save the campaign
    #When user clicks on the "Save" button
    Then verify the "Data Saved Successfully" pop up message is displayed.
    When user clicks on the "SUBMIT" button
    Then verify the "Data Submitted Successfully" pop up message is displayed.
    When user logout of the application.

    Examples: 
      | UserName      | Password   |
      | data.inputter | smplususer |
