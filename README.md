# camunda-spring-boot-quickstarter
Dockerized quick-start template for a Camunda EE Spring Boot process applications with Gradle.

**Note:** create a *gradle.properties* file in the root project folder with your own appropriate connection variables 
to get the camunda-bpm-ee artifacts for building the template.
```
CamundaNexusUser=your_username
CamundaNexusPassword=your_password
```

## H2 Database  
Add application-h2.yaml to application.yaml
Add the following dependency to build.gradle and remove postgres dependency.
```
implementation "com.h2database:h2"
```

## Postgres Database  
Add application-postgres.yaml to application.yaml
Add the following dependency to build.gradle and remove H2 dependency.
```
implementation "org.postgresql:postgresql"
```