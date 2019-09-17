package ch.umb.solutions.consulting.camundaspringbootquickstarter.service;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class AhoyService implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("\n\nAhoy, " + execution.getVariable("myVariable") + "!");
    }
}
