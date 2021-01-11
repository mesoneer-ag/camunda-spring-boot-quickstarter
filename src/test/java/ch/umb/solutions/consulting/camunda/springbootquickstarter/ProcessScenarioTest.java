package ch.umb.solutions.consulting.camunda.springbootquickstarter;

import ch.umb.solutions.consulting.camunda.springbootquickstarter.delegate.LoggerDelegate;
import ch.umb.solutions.consulting.camunda.springbootquickstarter.delegate.SampleDelegate;
import ch.umb.solutions.consulting.camunda.springbootquickstarter.service.IErpService;
import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.camunda.bpm.scenario.ProcessScenario;
import org.camunda.bpm.scenario.Scenario;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat;
import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.withVariables;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test case starting an in-memory database-backed Process Engine
 * including test coverage report in target folder
 */
@Deployment(resources = {
        "sample-process.bpmn"
})
public class ProcessScenarioTest {

    @Rule
    @ClassRule
    public static ProcessEngineRule rule =
            TestCoverageProcessEngineRuleBuilder.create()
                    .withDetailedCoverageLogging().build();


    private static final String PROCESS_DEFINITION_KEY = ProcessConstants.PROCESS_DEFINITION_SAMPLE_PROCESS;


    @Mock
    IErpService erpService;

    /* process variables */
    private Map<String, Object> variables;

    static {
        LogFactory.useSlf4jLogging(); // MyBatis
    }

    @Before
    public void setup() {
        // setup Mocks
        MockitoAnnotations.initMocks(this);

        // register delegates
        Mocks.register("loggerDelegate", new LoggerDelegate());
        Mocks.register("sampleDelegate", new SampleDelegate(erpService));

        // common  for all tests
        when(myProcess.waitsAtUserTask("UserTask_PurchaseItem")).thenReturn(task -> {
            assertThat(task).hasCandidateGroup("purchaser");
            task.complete();
        });
    }

    @Mock
    private ProcessScenario myProcess;

    @Test
    public void testHappyPath() {
        // initialize variables
        variables = Variables.createVariables()
                .putValue("request", "Expensive item")
                .putValue(ProcessVariables.VAR_AMOUNT.getVariableName(), 10_000);

        // define multiple when conditions
        when(myProcess.waitsAtUserTask("UserTask_ApproveRequest")).thenReturn(task -> {
            assertThat(task).hasCandidateGroup("approver");
            task.complete(withVariables("approvalType", "MANUALLY_APPROVED"));
        });

        // define scenarios by using camunda-bpm-assert-scenario
        Scenario scenario = Scenario.run(myProcess).startByKey(PROCESS_DEFINITION_KEY, variables).execute();

        // now you can do some assertions
        verify(myProcess).hasFinished("EndEvent_ItemPurchased");
        assertThat(scenario.instance(myProcess)).variables().containsEntry("approvalType", "MANUALLY_APPROVED");
    }

    @Test
    public void testAutoApproved() {
        // initialize variables
        variables = Variables.createVariables()
                .putValue("request", "Less expensive item")
                .putValue(ProcessVariables.VAR_AMOUNT.getVariableName(), 1000);

        // define scenarios by using camunda-bpm-assert-scenario
        Scenario scenario = Scenario.run(myProcess).startByKey(PROCESS_DEFINITION_KEY, variables).execute();

        // now you can do some assertions
        verify(myProcess).hasFinished("EndEvent_ItemPurchased");
        assertThat(scenario.instance(myProcess)).variables().containsEntry("approvalType", "AUTO_APPROVED");
    }

    @Test
    public void testManuallyDeclined() {
        // initialize variables
        variables = Variables.createVariables()
                .putValue("request", "Expensive item")
                .putValue(ProcessVariables.VAR_AMOUNT.getVariableName(), 100_000);

        // define multiple when conditions
        when(myProcess.waitsAtUserTask("UserTask_ApproveRequest")).thenReturn(task -> {
            assertThat(task).hasCandidateGroup("approver");
            task.complete(withVariables("approvalType", "MANUALLY_DECLINED"));
        });

        // define scenarios by using camunda-bpm-assert-scenario
        Scenario scenario = Scenario.run(myProcess).startByKey(PROCESS_DEFINITION_KEY, variables).execute();

        // now you can do some assertions
        verify(myProcess).hasFinished("Event_RequestDeclined");
        assertThat(scenario.instance(myProcess)).variables().containsEntry("approvalType", "MANUALLY_DECLINED");
    }

}
