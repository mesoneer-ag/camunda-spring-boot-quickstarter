package ch.umb.solutions.consulting.camundaspringbootquickstarter;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.scenario.ProcessScenario;
import org.camunda.bpm.scenario.Scenario;
import org.camunda.bpm.scenario.delegate.TaskDelegate;
import org.camunda.bpm.scenario.run.ProcessRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProcessApp.class})
public class ProcessScenarioTest {

  private static final String PROCESS_DEFINITION_KEY = "test1";

  static {
    LogFactory.useSlf4jLogging(); // MyBatis
  }

  @Autowired
  TaskService taskService;

  @Autowired
  RuntimeService runtimeService;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Mock
  private ProcessScenario myProcess;

  @Test
  public void testHappyPath() {
    // Define scenarios by using camunda-bpm-assert-scenario:

    ProcessRunner.ExecutableRunner starter = Scenario.run(myProcess)
        .startByKey(PROCESS_DEFINITION_KEY);

    // when(myProcess.waitsAtReceiveTask(anyString())).thenReturn((messageSubscription) -> {
    //  messageSubscription.receive();
    // });
     when(myProcess.waitsAtUserTask(anyString())).thenReturn(TaskDelegate::complete);

    // OK - everything prepared - let's go and execute the scenario
    Scenario scenario = starter.execute();

    // now you can do some assertions   
    verify(myProcess).hasFinished("EndEvent");
    //verify(myProcess).waitsAtTimerIntermediateEvent("IntermediateThrowEvent_10zmdtr");

  }

}
