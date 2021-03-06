**Supported JDK:** 1.8+ <br>
**Supported Platforms:** All platforms (i.e. Windows, Linux etc.) that Selenium and Sikuli supports. <br>
**Tested using JDK:** 1.8 <br>
**Tested using Web Browser:** Firefox, Chrome, InternetExplorer <br>
**Tested on Platforms:** Windows (7, 8, 10) <br>
**Main Dependencies:** TestNG, Cucumber, Sikuli, Selenium, Hibernate 

**Prime Testing Automation** is just a software application testing automation framework that provides the way to create/maintain testcases easily. It works on the concept **"Write Less Test More"**. This can be used 
* to perform end to end software testing automation for **web based** and **desktop** applications.
* to perform feature testing across multiple software applications.

This framework provides the implementation of standard UI components i.e. TextBox, RadioButton, ComboBox, TextArea etc. It uses the xpath approach to recognize the UI components on the web based applications that uses HTML. If any application that has custom UI components and can not be tested using standard UI components then you can create your own custom components using this framework classes.

This framework also provides interface with the databases with a very easy way. So you can validate your software application functionality against the data based on business logic.

# Why Prime Testing Automation Framework ?
* The main purpose of this framework is to standardize the automation of software application testing.
* Provides integrated software application testing automation solution that uses Java programming language.
* Used for rapid testcases development.
* It can be used for Black Box Testing, End to End Testing, Regression Testing, Functional Testing, Acceptance Testing, Integration Testing etc�
* Can be used to enhance software application quality.
* Can be used to reduce manual testing effort of software application during development / maintenance phase.

# Framework Features
* Component based approach
* DOM and Image base testing automation: It provides standard component implementation i.e. DataGrid, TextBox, ComboBox RadioButton, Hyperlink etc....
* Custom UI Component support: You can create your custom component. Please refer wiki page for more information.
* Multiple application support: You can configure multiple applications and can execute testcases across applications to test particular feature.
* Multiple user profile support / application
* Multiple database profile support / application
* Multiple web browser support: It supports all web browsers (i.e. Firefox, InternetExplorer, Chrome etc.) that selenium support.
* Support for UI Page screenshots: You can take screenshot in your testcase as and when it is required.
* Custom properties support in config files i.e. AppConfig.properties, TestConfig.properties, user profiles etc. 
* Modular, flexible & scalable design

# Code compilation
	- use the following command to clean the build
		> mvn clean
	- use the following command to create the jar file
		> mvn clean package
	- use the following command to install the jar file in maven repository.
		> mvn clean install

# Documentation & More Details
Please refer <a href="https://github.com/INDUS-Corporation/prime-test-fwk/wiki">wiki</a> pages for more details about this framework.

# License
<a href="http://www.gnu.org/licenses/lgpl-3.0.en.html" target="_blank">GNU LESSER GENERAL PUBLIC LICENSE, version 3</a>