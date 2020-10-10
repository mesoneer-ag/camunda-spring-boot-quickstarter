package ch.umb.solutions.consulting.camundaspringbootquickstarter;

import ch.umb.solutions.consulting.camundaspringbootquickstarter.mock.LoggerDelegateMock;
import ch.umb.solutions.consulting.camundaspringbootquickstarter.mock.MockHelper;
import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.assertions.bpmn.AbstractAssertions;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRule;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.junit4.SpringRunner;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;


/**
 * Test case starting an in-memory database-backed Process Engine using SpringBoot
 * without test coverage report.
 */
@RunWith(SpringRunner.class)
/**
 * https://docs.camunda.org/manual/7.13/user-guide/spring-boot-integration/testing/
 */
@SpringBootTest(
        properties = {
                "camunda.bpm.generate-unique-process-engine-name=true",
                "camunda.bpm.generate-unique-process-application-name=true",
                "camunda.bpm.job-execution.enabled=false",
                "spring.datasource.generate-unique-name=true"
                // additional properties...
        }
)
public class ProcessIntegrationTest  {

    private static final String PROCESS_DEFINITION_KEY = "job-executor-test";

    static {
        LogFactory.useSlf4jLogging(); // MyBatis
    }


    @Autowired
    ProcessEngine processEngine;

    @Autowired
    ApplicationContext context;

    @Before
    public void registerMocks() {
        MockHelper.replaceWithMock(context,"loggerDelegate", LoggerDelegateMock.class);
        init(processEngine);
    }

    @Test
    public void testHappyPath() {

       ProcessInstance processInstance = processEngine.getRuntimeService()
                .startProcessInstanceByKey(PROCESS_DEFINITION_KEY);

        execute(job(processInstance));
        execute(job(processInstance));
        execute(job(processInstance));

        assertThat(processInstance).isEnded();
    }

    @Test
    public void testExceptionPath() {

        ProcessInstance processInstance = processEngine.getRuntimeService()
                .startProcessInstanceByKey(PROCESS_DEFINITION_KEY);

        execute(job(processInstance));
        execute(job(processInstance));
        execute(job(processInstance));

        assertThat(processInstance).isEnded();
    }

    /**
     * Clean up the process engine from ThreadLocal to allow other test cases to run properly
     */
    @AfterClass
    public static void cleanUp() {
        AbstractAssertions.reset();
        ProcessEngines.destroy();
    }



}
