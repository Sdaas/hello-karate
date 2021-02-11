
[![Build Status](https://api.travis-ci.com/Sdaas/hello-karate.svg?branch=master)](https://travis-ci.com/Sdaas/hello-karate)
 
### Karate Starter

This Getting Started Guide shows how to setup a SpringBoot based REST service and test it using Karate (0.9.6) from 
within `IntelliJ`, `maven`, and `gradle`.

### The Rest Service

This is a standard Spring  Boot application that exposes two APIs

#### Hello API
```
$ curl localhost:8080/api/hello
Hello world!
```
The fancy version when a name is passed in as a parameter...
```
$ curl localhost:8080/api/hello?name=Daas
Hello Daas!
```

#### Person API 

Create a person
```
$ curl -X POST localhost:8080/api/person -H 'Content-type:application/json' -d '{"firstName": "John", "lastName" : "Doe", "age" : 30}'
42
```
Get a person by his/her id
```
$ curl localhost:8080/api/person/42
{"firstName":"John","lastName":"Doe","age":30}
```

### Setting Up Karate

The folder structure for Karate tests is given in the Karate documentation on
[folder structure](https://github.com/intuit/karate#folder-structure), but the 
summary is that:

* All tests are defined in `*.feature` files
* For every feature file package, you need to have an empty test-class in the same package under `src/test/java`
* Karate recommends to keep the `*.feature` files in the same folder as the test-class
* The `<build>` section of the `pom.xml` needs a small tweak for this ..
* (Similar change needed in build.gradle file)

In this example, we have two features `hello` and `person`. Their `*.feature` files and test-classes
are kept in `src/test/java/karate/hello` and  `src/test/java/karate/person` respectively

Note that the test-class file do NOT use the `*Test.java` file naming convention used by JUnit4 classes. This actually ensures
that these tests will not be picked up when when invoking mvn test (for the whole project) from the command line. 
But they can still be invoked from the IDE.

A `*.feature` file has the same syntax as [Gherkin/Cucumber](https://cucumber.io/docs/gherkin/reference/) 
and is also described in Karate [documentation](https://github.com/intuit/karate#script-structure). The
key points are 

* Lines that start with `#` are comments
* There are three sections
    * `Feature` : A name for the tests in this feature file
    * `Background` : The steps in this section are executed before every `Scenario` in that file.
    * `Scenario` : Each scenario is a test. A file can contain multiple Scenarios.
* Each scenario is described using
    * `Given` : setting up the test
    * `When` : the test action that will be performed
    * `Then` : the expected outcome(s)
    

The [`karate-config.js`](https://github.com/intuit/karate#karate-configjs) file in the `/test/java` folder contains the environment 
and global variables used by Karate. This is is basically a javascript function that returns
a JSON object. Which means that the file cannot contain any comment statements before the function body. 

We also add an empty test class file in `/test/java/karate/KarateTests` so that all karate tests can be
executed from the command-line. 

### Logging Configuration

Logging configuration is controlled by the `/test/java/logback.xml` file as explained in the Karate documentation
on [logging](https://github.com/intuit/karate#logging). 

# Setting up your Laptop

On Macs, you need to have an entry in your `/etc/hosts` file that contains an entry with your machine name. For example ...
```
127.0.0.1	localhost -MY-MACHINE-NAME-
```

This happens due to the way netty works in Karate. This issue is supposed to be fixed in Karate 1.0

### Running the tests

We have three types of tests - unit tests, Spring integration tests, and Karate tests. Ideally we want 
to be able to run them from both the command-line and the IDE. 

* Unit tests : are meant to run super fast
* Spring integration tests : run slower because the entire application context has to be created
* Karate tests : require the system under test to be running  

#### Running the Unit and Spring integration test
##### From IntelliJ

Right click on `/test/java/com.daasworld.hellokarate` and "Run all tests"

##### From command-line using Maven

Make sure that all the `.java` files in `com.daasworld` are configured to be treated as test classes. And 
to ignore all the tests in the `karate` folder.

```
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>2.22.1</version>
    <configuration>
        <excludes>
            <exclude>karate/**/*.java</exclude>
        </excludes>
        <includes>
            <include>com/**/*.java</include>
        </includes>
    </configuration>
</plugin>
```
To run the tests
```
mvn test
```
##### From command-line using Gradle
```
./gradlew test --tests com.daasworld.hellokarate.*
```

#### Running the Karate Tests
##### From IntelliJ

The Karate tests can also be invoked from within IntelliJ in multiple ways

* Right-click on `test/java/karate/KarateTests` to run all the tests
* Right-click on the individual runners (e.g., `test/java/karate/person/PersonRunner`) to run all the tests there
* Right-click on a `*.feature` file to run only that feature
* To run a single scenario, open the feature file, and right click on the specific scenario

The test results can be viewed in the browser at  `file:///<projectroot>/target/surefire-reports/karate-summary.html`

Note: 

* Right-clicking to run a `.feature` file will not work if the file path contains spaces (e.g, `~/Idea Projects/....`)
This is known bug in Karate. See [1283](https://github.com/intuit/karate/issues/1283)


##### From command-line using Maven

Karate does NOT start up the system under test. So first start up the application by running
```
$ mvn spring-boot:run
```
To run all the tests ( they are all under `karate`), run 
```
$ mvn test -Dtest=KarateTests
``` 
To run only the tests under the `karate/hello`, run
```
$ mvn test -Dtest=HelloRunner
```
To run only a single feature, specify it in the `karate.options` as shown below
```
$ mvn test "-Dkarate.options=classpath:karate/hello/hello1.feature" -Dtest=HelloRunner
```
To run only a single scenario, specify its line number as shown below
```
$ mvn test "-Dkarate.options=classpath:karate/hello/hello1.feature:13" -Dtest=HelloRunner
```

The test results can be viewed in the browser at  `file:///<projectroot>/target/surefire-reports/karate-summary.html`

##### From command-line using Gradle

Start up the application
```
$ ./gradlew clean bootRun
```

All the Karate tests are in the `karate.test` folder. To run these tests
```
$ ./gradlew test --tests KarateTests
```
To run only those tests in the `karate.hello` package 
```
$ ./gradlew test --tests HelloRunner
```
To run only the tests in `demo1.feature`
```
$ ./gradlew test --tests HelloRunner -Dkarate.options=classpath:karate/hello/hello1.feature
```
To run only one scenario, you need to specify the line number, as shown below
```
$ ./gradlew test --tests HelloRunner -Dkarate.options=classpath:karate/hello/hello1.feature:7
```

The test results can be viewed in the browser at  `file:///<projectroot>/build/surefire-reports/karate-summary.html`

Note : The test report from the IDE and the command-line are generated in DIFFERENT places. Reports for test run from the IDE are stored
in the `target` folder. Report for tests run from the command-line are stored in the `build` folder. 

### References

* [Karate](https://github.com/intuit/karate) github repo
* [Spring Boot](https://spring.io/projects/spring-boot)


