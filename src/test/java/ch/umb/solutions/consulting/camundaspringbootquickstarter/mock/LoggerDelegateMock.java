package ch.umb.solutions.consulting.camundaspringbootquickstarter.mock;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class LoggerDelegateMock implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution)  {
        System.out.println("Mock service");
    }
}
