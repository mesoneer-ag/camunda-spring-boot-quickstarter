package ch.umb.solutions.consulting.camunda.springbootquickstarter;

import ch.umb.solutions.consulting.camunda.springbootquickstarter.delegate.LoggerDelegate;
import ch.umb.solutions.consulting.camunda.springbootquickstarter.delegate.SampleDelegate;
import ch.umb.solutions.consulting.camunda.springbootquickstarter.service.IErpService;
import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;


/**
 * Test case starting a light-weight in-memory database-backed Process Engine
 * including test coverage report in target folder
 */
public class ProcessUnitTest {

    @Rule
    @ClassRule
    public static ProcessEngineRule processEngine =
            TestCoverageProcessEngineRuleBuilder.create()
                    .withDetailedCoverageLogging().build();

    private static final String PROCESS_DEFINITION_KEY = "sample-process";

    static {
        LogFactory.useSlf4jLogging(); // MyBatis
    }

    @Mock
    IErpService erpService;

    /* process variables */
    private Map<String, Object> variables;

    @Before
    public void registerCommonDelegates() {
        // setup Mocks
        MockitoAnnotations.initMocks(this);

        // register delegates
        Mocks.register("loggerDelegate", new LoggerDelegate());
        Mocks.register("sampleDelegate", new SampleDelegate(erpService));
    }

    @Test
    @Deployment(resources = {"sample-process.bpmn"})
    public void testHappyPath() {
        // initialize variables
        variables = Variables.createVariables()
                .putValue("request", "Expensive item")
                .putValue(ProcessVariables.VAR_AMOUNT.getVariableName(), 10_000);

        ProcessInstance processInstance = processEngine.getRuntimeService()
                .startProcessInstanceByKey(PROCESS_DEFINITION_KEY,variables);

        // manually approve task
        complete(task(processInstance), withVariables("approvalType", "MANUALLY_APPROVED"));

        // complete purchase task
        complete(task(processInstance));

        assertThat(processInstance).isEnded();
    }

}
