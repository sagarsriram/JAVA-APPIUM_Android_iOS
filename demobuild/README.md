# AutoHawk Automation Framework.

Framework for testing mobile applications using **Appium**, **TestNG**, and **Java**. 
This framework is designed for maintainability, scalability, and ease of execution across different environments
including local devices and BrowserStack.

## Features
- Appium support for Android & iOS
- TestNG-based test structure
- Page Object Model (POM) architecture
- Integrated reporting
- Maven build tool
- Logs and screenshots for failed tests

## Prerequisites
- Java 11+
- Maven
- Appium Server running
- Android Emulator or Real Device
- IOS Simulator or Real Device
- Optional: BrowserStack credentials
- APK/IPA file (stored externally, not committed)

### Configuration
- Updated desired capabilites in base driver classes or a config file
- APK/IPA/APP paths are configures through env variables ot test config
- Use browserstack.yml for cloud execution setup

## How to Run Tests
``` bash
## To run a specific TestNG Suite:
mvn test -DsuiteXmlFile=testNGSuites/smokeSuite.xml

## Run all tests
 mvn clean test


 


