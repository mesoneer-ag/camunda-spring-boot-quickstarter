# camunda-spring-boot-quickstarter
Dockerized quick-start template for a Camunda EE Spring Boot process applications with Gradle.

**Note:** create a *gradle.properties* file in the root project folder with your own appropriate connection variables 
to get the camunda-bpm-ee artifacts for building the template.
```
CamundaNexusUser=your_username
CamundaNexusPassword=your_password
```

## H2 Support 
Add the following dependency for H2 support to build.gradle and remove postgres dependency.
Also delete application-postgres.yaml if you do not use postgres.
```
implementation "com.h2database:h2"
```