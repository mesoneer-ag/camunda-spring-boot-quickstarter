package ch.umb.solutions.consulting.camunda.springbootquickstarter.mock;

import ch.umb.solutions.consulting.camunda.springbootquickstarter.delegate.LoggerDelegate;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerDelegateMock implements JavaDelegate {

    final static Logger logger = LoggerFactory.getLogger(LoggerDelegate.class);

    @Override
    public void execute(DelegateExecution execution)  {
        logger.info("mock invoked by"
                + ": processDefinitionName=" + execution.getProcessEngineServices().getRepositoryService().getProcessDefinition(execution.getProcessDefinitionId()).getName()
                + ", processDefinitionId=" + execution.getProcessDefinitionId()
                + ", activtyId=" + execution.getCurrentActivityId()
                + ", activtyName='" + execution.getCurrentActivityName() + "'"
                + ", processInstanceId=" + execution.getProcessInstanceId()
                + ", businessKey=" + execution.getProcessBusinessKey()
                + ", executionId=" + execution.getId());
    }
}
