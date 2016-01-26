**Prime Testing Automation** is just a framework that provides the way to create/maintain testcases easily. It works on the concept **"Write Less Test More"**. This can be used 
* to perform end to end software testing automation for web based and desktop applications.
* to perform feature testing across multiple software applications.

This framework provides the implementation of standard UI components i.e. TextBox, RadioButton, ComboBox, TextArea etc. It uses the xpath approach to recognize the UI components on the web based applications that uses HTML. If any application that has custom UI components and can not be tested using standard UI components then you can create your own custom components using this framework classes.

This framework also provides interface with the databases with a very easy way. So you can validate your software application functionality against the data based on business logic.

# Why Prime Testing Automation Framework ?
* Provides integrated testing automation solution that uses Java programming language.
* Used for rapid testcases development.
* It can be used for End to End Testing, Regression Testing, Functional Testing, Acceptance Testing, Integration Testing etc…
* Can be used to enhance software application quality.
* Can be used to reduce manual testing effort of software application during development / maintenance phase.

# Framework Features
* Component based approach
* DOM and Image base testing automation: It provides standard component implementation i.e. TextBox, ComboBox RadioButton, Hyperlink etc....
* Custom UI Component support: You can create your custom component. Please refer wiki page for more information.
* Multiple application support: You can configure multiple applications and can execute testcases across applications to test particular feature.
* Multiple user profile support / application
* Multiple database profile support / application
* Multiple web browser support: It supports all web browsers (i.e. Firefox, InternetExplorer, Chrome etc.) that selenium support.
* Support for UI Page screenshots: You can take screenshot in your testcase as and when it is required.
* Custom properties support in config files i.e. AppConfig.properties, TestConfig.properties, user profiles etc. 
* Modular, flexible & scalable design
* Implementation of this framework tries to detect the problem as early as possible. Lets say you wanted to type big string in textbox and somehow the whole string is not typed (due to length restriction), this will fail the testcase that time. This kind of many scenarios it has implemented.

# Code compilation
	- use the following command to clean the build
		> mvn clean
	- use the following command to create the jar file
		> mvn clean package
	- use the following command to install the jar file in maven repository.
		> mvn clean install

# Documentation & More Details
Please refer wiki pages for more details about this framework.