package ch.umb.solutions.consulting.camundaspringbootquickstarter;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication
public class ProcessApp {

    public static void main(String... args) {
        SpringApplication.run(ProcessApp.class);
    }

}