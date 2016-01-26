**Prime Testing Automation** is just a framework that provides the way to create/maintain testcases easily. It works on the concept **"Write Less Test More"**. This can be used 
* to perform end to end software testing automation for web based and desktop applications.
* to perform feature testing across multiple software applications.

This framework provides the implementation of standard UI components i.e. TextBox, RadioButton, ComboBox, TextArea etc. It uses the xpath approach to recognize the UI components on the web based applications that uses HTML. If any application that has custom UI components and can not be tested using standard UI components then you can create your own custom components using this framework classes.

This framework also provides interface with the databases with a very easy way. So you can validate your software application functionality against the data based on business logic.

# Why Prime Testing Automation Framework ?
* Provides integrated testing automation solution that uses Java programming language.
* Used for rapid testcases development.
* It can be used for End to End Testing, Regression Testing, Functional Testing, Acceptance Testing etc…
* Can be used to enhance software application quality.
* Can be used to reduce manual testing effort of software application during development / maintenance phase.

# Framework Features
* DOM and Image base testing automation
* Multiple application support
* Multiple user profile support / application
* Multiple database profile support / application
* Multiple web browser support
* Support for UI Page screenshots
* Custom UI Component support
* Modular, flexible & scalable design

# Code compilation
	- use the following command to clean the build
		> mvn clean
	- use the following command to create the jar file
		> mvn clean package
	- use the following command to install the jar file in maven repository.
		> mvn clean install