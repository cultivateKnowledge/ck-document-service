# dropwizard-gradle-archetype

Buildout of ideas from Modern Java Series

http://blog.paralleluniverse.co/2014/05/01/modern-java/<br/>
http://blog.paralleluniverse.co/2014/05/08/modern-java-pt2/<br/>
http://blog.paralleluniverse.co/2014/05/15/modern-java-pt3/

Contains:

* Gradle based web service
* Based on DropWizard
* Uses dagger for Dependency Injection
* Uses jdbi for database interaction
* Uses jolokia for monitoring
* Uses jacoco for code coverage
* Uses Cucumber for BDD testing

<pre>java -Ddropwizard.config=./src/main/resources/appconfig.yml -jar ./build/libs/dropwizard-gradle-archetype-0.1.0-fatJar.jar</pre>


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

http://localhost:8080/db/all

http://localhost:8080/db/item/1

http://localhost:8080/hello-world

http://localhost:8080/hello-world?name=Bob


## Mongo Tutorials
http://docs.mongodb.org/ecosystem/tutorial/getting-started-with-java-driver/
http://mongojack.org/tutorial.html
