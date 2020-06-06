Feature: Verification of End to End Flow for Estate
  Description: 
  PreCondition: 1.Data inputter should have configured to farmergroup product origin
  2.Campaign should be currently running inorder to sumbit the data

  @test
  Scenario Outline: To Verify the datasubmission for Estate as datainputter
    Given Customer opens the application.
    When Customer enters email as "<UserName>" and password as "<Password>" and logged in.
    When user selects the product "Coffee" from dropdown.
    When user selects the origin "Tanzania" from dropdown.
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
    #When user submit the campaign.
    When user save the campaign
    #When user clicks on the "Save" button
    Then verify the "Data Saved Successfully" pop up message is displayed.
    When user clicks on the "SUBMIT" button
    Then verify the "Data Submitted Successfully" pop up message is displayed.
    When user logout of the application.

    Examples: 
      | UserName      | Password   |
      | data.inputter | smplususer |

  
  @test
  Scenario Outline: To Verify the approval of farmergroup submission by datainputter by origin approver
    Given Customer opens the application
    When Customer enters email as "<UserName>" and password as "<Password>"
    When user clicks on the "Data Approval" tab
    When user selects the origin "Tanzania".
    When user selects the product "Coffee".
    When user selects the providedby "data inputter".
    When user clicks on the "Approve" button
    Then verify the "All the Cells will be Approved" pop up message is displayed.
    When user clicks on the "Proceed" button
    Then verify the "Successfully Approved By Origin Approver" pop up message is displayed.
    When user logout of the application

    Examples: 
      | UserName        | Password   |
      | origin.approver | smplususer |

  @test
  Scenario Outline: To Verify the approval of farmergroup submission by datainputter by product approver
    Given Customer opens the application
    When Customer enters email as "<UserName>" and password as "<Password>"
    #When user clicks on the "Data Approval" tab
    When user selects the product "Coffee".
    When user selects the origin "Tanzania".
    When user selects the providedby "data inputter".
    When user clicks on the "Approve" button
    Then verify the "All the Cells will be Approved" pop up message is displayed.
    When user clicks on the "Proceed" button
    Then verify the "Successfully Approved By Product Approver" pop up message is displayed.
    When user clicks on the "Reject Approved Data" button
    When user selects the product "Coffee".
    When user selects the origin "Tanzania".
    When user selects the providedby "data inputter".
    When user clicks on the "Reject" button
    When user reject the below data in column "2" of the selected estate
      | Field  | Value   |
      | KM-131 | Testing |
    When user clicks on the "Proceed" button
    Then verify the "Rejected By Product Approver" pop up message is displayed.
    When user logout of the application

    Examples: 
      | UserName         | Password   |
      | product.approver | smplususer |

  @test
  Scenario Outline: To Verify the rejected datasubmission for Estate is available in datainputter
    Given Customer opens the application.
    When Customer enters email as "<UserName>" and password as "<Password>" and logged in.
    When user selects the product "Coffee" from dropdown.
    When user selects the origin "Tanzania" from dropdown.
    Then verify the "Estate" text is displayed.
    When user logout of the application.

    Examples: 
      | UserName      | Password   |
      | data.inputter | smplususer |
