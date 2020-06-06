Feature: Verification of End to End Flow for Farmer group
  Description: 
  PreCondition: 1.Data inputter,orign Approverand product approver should have configured to farmergroup product origin
  2.Campaign should be currently running inorder to sumbit the data

  @sanity
  Scenario Outline: To Verify the datasubmission for farmergroup as datainputter
    Given Customer opens the application
    When Customer enters email as "<UserName>" and password as "<Password>"
    When user selects the product "Cashew".
    When user selects the origin "Brazil".
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
    Then verify the "Data Sumitted Successfully" pop up message is displayed.
    When user logout of the application.

    Examples: 
      | UserName      | Password   |
      | data.inputter | smplususer |

  @sanity
  Scenario Outline: To Verify the approval of farmergroup submission by datainputter by origin approver
    Given Customer opens the application
    When Customer enters email as "<UserName>" and password as "<Password>"
    When user clicks on the "Data Approval" tab
    When user selects the origin "Brazil".
    When user selects the product "Cashew".
    When user selects the providedby "data inputter".
    When user clicks on the "Approve" button
    Then verify the "All the Cells will be Approved" pop up message is displayed.
    When user clicks on the "Proceed" button
    Then verify the "Successfully Approved By Origin Approver" pop up message is displayed.
    When user logout of the application

    Examples: 
      | UserName        | Password   |
      | origin.approver | smplususer |

  @sanity
  Scenario Outline: To Verify the approval of farmergroup submission by datainputter by product approver
    Given Customer opens the application
    When Customer enters email as "<UserName>" and password as "<Password>"
    #When user clicks on the "Data Approval" tab
    When user selects the product "Cashew".
    When user selects the origin "Brazil".
    When user selects the providedby "data inputter".
    When user clicks on the "Approve" button
    Then verify the "All the Cells will be Approved" pop up message is displayed.
    When user clicks on the "Proceed" button
    Then verify the "Successfully Approved By Product Approver" pop up message is displayed.
   When user clicks on the "Reject Approved Data" button
    When user selects the product "Cashew".
    When user selects the origin "Brazil".
    When user selects the providedby "data inputter".
    When user clicks on the "Reject" button
    When user reject the below data in column "2" of the selected estate
      | Field  | Value   |
      | KM-47 | Testing |
    When user clicks on the "Proceed" button
    Then verify the "Rejected By Product Approver" pop up message is displayed.
    When user logout of the application

    Examples: 
      | UserName         | Password   |
      | product.approver | smplususer |

  @sanity
  Scenario Outline: To Verify the rejected datasubmission for Estate is available in datainputter
    Given Customer opens the application.
    When Customer enters email as "<UserName>" and password as "<Password>" and logged in.
    When user selects the product "Cashew" from dropdown.
    When user selects the origin "Brazil" from dropdown.
    Then verify the "Farmer Group" text is displayed.
    When user logout of the application.

    Examples: 
      | UserName      | Password   |
      | data.inputter | smplususer |
