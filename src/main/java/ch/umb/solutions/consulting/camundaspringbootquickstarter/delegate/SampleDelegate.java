package ch.umb.solutions.consulting.camundaspringbootquickstarter.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static ch.umb.solutions.consulting.camundaspringbootquickstarter.ProcessVariables.VAR_SAMPLE;


/**
 * Sample deleagate
 */
@Component
public class SampleDelegate implements JavaDelegate {

    final static Logger logger = LoggerFactory.getLogger(SampleDelegate.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        logger.debug("Get variable: " + VAR_SAMPLE.getVariableName());
        var s = VAR_SAMPLE.get(execution);

        logger.debug("Set variable: " + VAR_SAMPLE.getVariableName());
        VAR_SAMPLE.set(execution, s + " new value");
    }
}
