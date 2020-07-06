package ch.umb.solutions.consulting.camundaspringbootquickstarter;

import ch.umb.solutions.consulting.camundaspringbootquickstarter.mock.LoggerDelegateMock;
import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.assertions.bpmn.AbstractAssertions;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.junit.*;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;


/**
 * Test case starting a light-weight in-memory database-backed Process Engine
 * including test coverage report in target folder
 */
public class ProcessUnitTest  {

    @Rule
    @ClassRule
    public static ProcessEngineRule processEngine =
            TestCoverageProcessEngineRuleBuilder.create()
                    .withDetailedCoverageLogging().build();

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

    /** Clean up the process engine from ThreadLocal to allow other test cases to run properly */
    @AfterClass
    public static void cleanUp() {
        AbstractAssertions.reset();
    }

}
