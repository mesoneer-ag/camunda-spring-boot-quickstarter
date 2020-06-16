package ch.umb.solutions.consulting.camundaspringbootquickstarter;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;



/**
 * Test case starting an in-memory database-backed Process Engine.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProcessApp.class})
public class ProcessUnitTest {

  private static final String PROCESS_DEFINITION_KEY = "job-executor-test";

  static {
    LogFactory.useSlf4jLogging(); // MyBatis
  }

  @Autowired
  TaskService taskService;

  @Autowired
  RuntimeService runtimeService;


  @Test
  public void testHappyPath() {
    // Either: Drive the process by API and assert correct behavior by camunda-bpm-assert, e.g.:
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY);
    assertTrue(true);
    // Now: Drive the process by API and assert correct behavior by camunda-bpm-assert
  }

}
