# Cultivate Knowledge - Document Service

Contains:

* Gradle based web service
* Based on DropWizard
* Uses jolokia for monitoring
* Uses jacoco for code coverage
* Uses Cucumber for BDD testing
* Uses Swagger for API documentation and testing
* Uses MongoDB for data storage

<pre>java -Ddropwizard.config=./src/main/resources/appconfig.yml -jar ./build/libs/ck-document-service-0.1.0-fatJar.jar</pre>


## Gradle Quickstart

// To compile and test<br/>
 gradle test
  
//  To compile, test, and generate coverage reports<br/>
 gradle test jacocoTestReport

// To run the webapp<br/>
 gradle run

// To create a fatJar for deployment<br/>
 gradle fatJar
 
 // To run with JPDA Debugger Enabled (port 5005)<br/>
 gradle -DDEBUG=true run
 
## Service Examples

http://localhost:8080/


## Mongo Tutorials
http://docs.mongodb.org/ecosystem/tutorial/getting-started-with-java-driver/

http://mongojack.org/tutorial.html
