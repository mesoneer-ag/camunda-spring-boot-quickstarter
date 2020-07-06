# camunda-spring-boot-quickstarter
Dockerized quick-start template for a Camunda EE 7.13.1 Spring Boot process applications with Gradle.
Support for Camunda process testing including process test coverage report for non Sprint Boot tests.

## Getting Started
**Note:** create a *gradle.properties* file in the root project folder with your own appropriate connection variables 
to get the camunda-bpm-ee artifacts for building the template.
```
CamundaNexusUser=your_username
CamundaNexusPassword=your_password
```

### H2 Database  
Add application-h2.yaml to application.yaml
Add the following dependency to build.gradle and remove postgres dependency.
```
implementation "com.h2database:h2"
```

### Postgres Database  
Add application-postgres.yaml to application.yaml
Add the following dependency to build.gradle and remove H2 dependency.
```
implementation "org.postgresql:postgresql"
```
### Testing 
The process test coverage report works for Camunda unit tests. The Spring Boot 
tests  do not generate a process test coverage report.
![Process Test Coverage](process_test_coverage_report.png)
**Note:**
When running both Camunda spring boot integration tests and Camunda unit tests in parallel,
please do not forget to clean-up the current process engine using @AfterClass annotation.
