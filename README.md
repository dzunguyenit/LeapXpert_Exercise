Automation Page Object Model Framework - Using Java, Selenium, Appium, Maven, Cucumber, Cucumber Report. This project is to provide a good starting structure point for those looking to use Java, Selenium and some other extensions.

Resources

Selenium, Appium, Maven, Cucumber, Cucumber Report Examples Demo suites: Register successful with new account Incorporated/UnIncorporated /*

SCENARIO: Login_Chat_Message: 

1. User 1 (automation_auto_31) logs in the Web Application https://web.qa.leapxpert.app/ 
2. User 1 requests the QR code from the Web Application to allow to log in the Mobile App 
3. On the mobile app, User 1 starts the mobile app then input QR code 
4. User 1 inputs the username and password on Mobile application 
5. User 1 inputs OTP 111111 
6. User 1 goes to Contact tab 
7. User 1 searches for User 2
8. User 1 sends message to User 2 
9. User 2 logs in on Web application and verifies that he has received the message

Pre-requisites Tools & Libs: Java 8 or Above Right-click "Pom.xml" > Maven > Reimport Intellij Import Maven Project

Running Tests Edit runAspire.xml to run suite

Intellij menu, click Build > Build Project

Pre-requisites:

Start appium server by command line:

$ appium

Result is displayed:

[Appium] Welcome to Appium v1.21.0
[Appium] Appium REST http interface listener started on 0.0.0.0:4723

Open with new tab command line:
$ mvn clean compile

Run the test from file "runLeapXpert.bat"
Command Line Run the test

$ mvn test verify

Result:
Cucumber report: 

https://github.com/dzunguyenit/LeapXpert_Exercise/blob/master/ReportsImage/CucumberReport.png