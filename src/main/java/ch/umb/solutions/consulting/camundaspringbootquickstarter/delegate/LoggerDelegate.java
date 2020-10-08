package ch.umb.solutions.consulting.camundaspringbootquickstarter.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static ch.umb.solutions.consulting.camundaspringbootquickstarter.ProcessVariables.VAR_LOGMESSAGE;

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
    }

}