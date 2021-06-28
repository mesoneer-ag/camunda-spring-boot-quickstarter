package ch.umb.solutions.consulting.camunda.springbootquickstarter.delegate;

import static ch.umb.solutions.consulting.camunda.springbootquickstarter.ProcessVariables.VAR_APPROVAL_TYPE;
import static ch.umb.solutions.consulting.camunda.springbootquickstarter.ProcessVariables.VAR_BAZ;
import static ch.umb.solutions.consulting.camunda.springbootquickstarter.ProcessVariables.VAR_FOO;
import static ch.umb.solutions.consulting.camunda.springbootquickstarter.ProcessVariables.VAR_SAMPLE;

import ch.umb.solutions.consulting.camunda.springbootquickstarter.domain.ApprovalType;
import ch.umb.solutions.consulting.camunda.springbootquickstarter.service.IErpService;
import javax.inject.Inject;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * Sample delegate
 */
@Component
public class SampleDelegate implements JavaDelegate {

    private IErpService erpService;

    @Inject
    public SampleDelegate(IErpService erpService){
        this.erpService = erpService;
    }

    final static Logger logger = LoggerFactory.getLogger(SampleDelegate.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        logger.debug("Get variable: " + VAR_SAMPLE.getVariableName());
        var s = VAR_SAMPLE.get(execution);

        var foo = VAR_FOO.get(execution);

        logger.debug("foo: "+ foo.toString());

        erpService.updateErp(s);
        logger.debug("Set variable: " + VAR_SAMPLE.getVariableName());
        VAR_SAMPLE.set(execution, s + " new value");
        var baz = VAR_BAZ.get(execution);

        /**
         * this does'nt work, because the variable is set directly in the BPMN as String and can not be cast
         *  a possible solution is documented in the ProcessVariable
         *
         */
//        var approvalType = VAR_APPROVAL_TYPE.get(execution);

        VAR_SAMPLE.set(execution, s + " new value");
    }
}
