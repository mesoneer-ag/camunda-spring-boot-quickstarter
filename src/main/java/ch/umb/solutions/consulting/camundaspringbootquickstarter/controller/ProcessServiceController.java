package ch.umb.solutions.consulting.camundaspringbootquickstarter.controller;

import ch.umb.solutions.consulting.camundaspringbootquickstarter.service.AhoyService;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessServiceController {

    final AhoyService ahoyService;

    public ProcessServiceController(AhoyService ahoyService) {
        this.ahoyService = ahoyService;
    }

    @Bean
    public JavaDelegate ahoyDelegate() {
        return ahoyService;
    }
}