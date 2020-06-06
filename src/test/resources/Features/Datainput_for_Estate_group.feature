Feature: To Verify the datasubmission for Estate as datainputter
  Description: 
  PreCondition: 1.Data inputter should have configured to farmergroup product origin
  2.Campaign should be currently running inorder to sumbit the data

  @regression
  Scenario Outline:  To Verify the datasubmission for Estate as datainputter
    Given Customer opens the application.
    When Customer enters email as "<UserName>" and password as "<Password>" and logged in.
    When user selects the product "Almond" from dropdown.
    When user selects the origin "Australia" from dropdown.
    When user enters the below data in column "1" of the selected estate.
      | Field              | Value   |
      | Estate             | Testing |
      | Baseline Data Year |    2018 |
      | Current Data Year  |    2019 |
    When user enters the below data in column "5" of the below KM's.
      | Field  | Value |
      | KM-131 |    10 |
      | KM-132 |    10 |
      | KM-133 |    10 |
      | KM-146 |    10 |
      | KM-156 |    10 |
      | KM-164 |    10 |
      | KM-167 |    10 |
      | KM-170 |    10 |
      | KM-172 |    10 |
    When user save the campaign.
    #When user clicks on the "Save" button
    Then verify the "Data Saved Successfully" pop up message is displayed.
    When user clicks on the "SUBMIT" button
    Then verify the "Data Submitted Successfully" pop up message is displayed.
    When user logout of the application.

    Examples: 
      | UserName      | Password   |
      | data.inputter | smplususer |
