package ch.umb.solutions.consulting.camundaspringbootquickstarter;

import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Service {

  @Bean
  public JavaDelegate ahoyService() {
    return execution -> System.out.println("\n\nAhoy, " + execution.getVariable("myVariable") + "!");
  }

}