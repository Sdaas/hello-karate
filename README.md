[![Java CI with Maven](https://github.com/Sdaas/hello-karate/actions/workflows/maven.yml/badge.svg)](https://github.com/Sdaas/hello-karate/actions/workflows/maven.yml)

## Karate Starter

This Getting Started Guide shows how to setup a SpringBoot based REST service and test it using Karate from 
within `IntelliJ`, `maven`, and `gradle`.

## The Rest Service

This is a standard Spring  Boot application that exposes two APIs

### Hello API
```
$ curl localhost:8080/api/hello
Hello world!
```
The fancy version when a name is passed in as a parameter...
```
$ curl localhost:8080/api/hello?name=Daas
Hello Daas!
```
### Person API 

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

## Setting Up Karate

The folder structure for Karate tests is given in the Karate documentation on
[folder structure](https://github.com/intuit/karate#folder-structure), but the 
summary is that:

* All tests are defined in `*.feature` files
* For every feature file package, you need to have an empty test-class in the same package under `src/test/java`
* Karate recommends keeping the `*.feature` files in the same folder as the test-class
* The `<build>` section of the `pom.xml` needs a small tweak for this ..
* A similar change needed in `build.gradle` file.


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


### Logging Configuration

Logging configuration is controlled by the `/test/java/logback.xml` file as explained in the Karate documentation
on [logging](https://github.com/intuit/karate#logging). 

## Setting up your Laptop

On Macs, you need to have an entry in your `/etc/hosts` file that contains an entry with your machine name. For example ...
```
127.0.0.1	localhost -MY-MACHINE-NAME-
```

This happens due to the way netty works in Karate. This issue is supposed to be fixed in Karate 1.0

## Running the tests

This repo contains a mix of unit tests (e.g., `PersonServiceTests.java`), springboot tests using TestRestTemplate
(e.g, `GreetingControllerTests.java`) and Karate Tests (`*.feature` file). There is also `KarateTests.java` which 
is a springboot tests that invokes all the karate tests.

There is no need to start the application when running the tests - that will happen automatically.

* From within IntelliJ ...
  * Right click on `src/test/java` and select "Run All Tests"
  * The test results can be viewed in the browser at  `file:///<projectroot>/target/karate-reports/karate-summary.html`
* From command line using Maven ...
  * Make sure that `JAVA_HOME` environment variable is pointing to Java 11
  * `mvn clean test`
* From command line using Gradle ...
  * `./gradlew clean test`

## Starting only the application 

* IntelliJ -> Run -> HelloKarateApplication
* `mvn spring-boot:run`
* `./gradlew clean bootRun`

## Developer Instructions

This repo uses github actions to automatically build and test the app whenever code is pushed to
master branch. See [maven.yml](.github/workflows/maven.yml)

### Release process

* Make sure that all files are committed
* `mvn test`
* `mvn release:prepare -DdryRun=true`
* `mvn release:clean`
* `mvn release:prepare`
* `mvn release:perform`
* Assuming that we are releasing `A.B.C`
  * At this point the pom.xml should have been updated to the `next-version-SNAPSHOT` version
  * There should a github tag called `vA.B.C`
* We still need to create a Release (and release notes) manually from this tag.

### References

* [Karate DSL ](https://github.com/intuit/karate)
* [Karate 1.0 Upgrade Guide](https://github.com/karatelabs/karate/wiki/1.0-upgrade-guide)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Quickstart for Github Actions](https://docs.github.com/en/actions/quickstart?utm_source=pocket_mylist)
* [Maven Release Plugin](https://maven.apache.org/maven-release/maven-release-plugin/index.html)
