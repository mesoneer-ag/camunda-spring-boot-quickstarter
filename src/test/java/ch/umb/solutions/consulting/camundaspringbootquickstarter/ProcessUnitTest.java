package ch.umb.solutions.consulting.camundaspringbootquickstarter;

import ch.umb.solutions.consulting.camundaspringbootquickstarter.mock.LoggerDelegateMock;
import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest;
import org.junit.Before;
import org.junit.Test;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;



/**
 * Test case starting a light-weight in-memory database-backed Process Engine without SpringBoot
 */
public class ProcessUnitTest extends AbstractProcessEngineRuleTest {

    private static final String PROCESS_DEFINITION_KEY = "job-executor-test";

    static {
        LogFactory.useSlf4jLogging(); // MyBatis
    }

    @Before
    public void registerCommonDelegates() {
        Mocks.register("loggerDelegate", new LoggerDelegateMock());
    }

    @Test
    @Deployment(resources = {"job-executor-test.bpmn"})
    public void testHappyPath() {
        ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey(PROCESS_DEFINITION_KEY);

        execute(job(processInstance));
        execute(job(processInstance));
        execute(job(processInstance));

        assertThat(processInstance).isEnded();
    }

}
