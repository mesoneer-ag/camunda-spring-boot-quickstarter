package ch.umb.solutions.consulting.camunda.springbootquickstarter.delegate;


import ch.umb.solutions.consulting.camunda.springbootquickstarter.domain.Bar;
import ch.umb.solutions.consulting.camunda.springbootquickstarter.domain.Baz;
import ch.umb.solutions.consulting.camunda.springbootquickstarter.domain.Foo;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static ch.umb.solutions.consulting.camunda.springbootquickstarter.ProcessVariables.VAR_BAZ;
import static ch.umb.solutions.consulting.camunda.springbootquickstarter.ProcessVariables.VAR_BAZ2;
import static ch.umb.solutions.consulting.camunda.springbootquickstarter.ProcessVariables.VAR_BAZ3;
import static ch.umb.solutions.consulting.camunda.springbootquickstarter.ProcessVariables.VAR_FOO;
import static ch.umb.solutions.consulting.camunda.springbootquickstarter.ProcessVariables.VAR_LOGMESSAGE;

/**
 * Simple Logger delegate implementation that can be used
 * from within a BPMN 2.0 Service Task.
 */
@Component
public class LoggerDelegate implements JavaDelegate {

    final static Logger logger = LoggerFactory.getLogger(LoggerDelegate.class);

    @Override
    public void execute(DelegateExecution execution) {
        String logMessage = VAR_LOGMESSAGE.get(execution);

        logger.info("logMessage='" + logMessage + "'"
                + ", processDefinitionName=" + execution.getProcessEngineServices().getRepositoryService().getProcessDefinition(execution.getProcessDefinitionId()).getName()
                + ", processDefinitionId=" + execution.getProcessDefinitionId()
                + ", activtyId=" + execution.getCurrentActivityId()
                + ", activtyName='" + execution.getCurrentActivityName() + "'"
                + ", processInstanceId=" + execution.getProcessInstanceId()
                + ", businessKey=" + execution.getProcessBusinessKey()
                + ", executionId=" + execution.getId());

        Bar bar = new Bar(true,5);
        Foo foo = new Foo("foobar", 5, bar);

        try {
            VAR_FOO.set(execution, foo);
            VAR_BAZ.set(execution, Baz.TWO);
            VAR_BAZ2.set(execution, Baz.TWO);
            VAR_BAZ3.set(execution, Baz.TWO.name());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
