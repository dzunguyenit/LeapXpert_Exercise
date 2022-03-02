@ChatFeature
Feature: Payment feature
  As an automation tester
  I want a payment function
  So that I want to make sure payment correctly

  @Login_Chat_Message
  Scenario: Create account, login and verify login successfully
    Given I navigate to Web Application "https://web.qa.leapxpert.app/" with company "auto_testing", username "automation_auto_2021" and password "Testing@123"
    When User01 requests the QR code from the Web Application
    When User01 lanch app and input QR code
    Then Quit app and website


#  4. User 1 inputs the username and password on Mobile application
#  5. User 1 inputs OTP 111111
#  6. User 1 goes to Contact tab
#  7. User 1 searches for User 2
