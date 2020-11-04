package ch.umb.solutions.consulting.camunda.springbootquickstarter;

import ch.umb.solutions.consulting.camunda.helper.ProcessVariable;

import static ch.umb.solutions.consulting.camunda.helper.ProcessVariable.create;

public class ProcessVariables {
    /**
     * The following three are just demo variables and can be deleted in your project.
     */
    public static final ProcessVariable<String> VAR_SAMPLE = create("sampleVar");
    public static final ProcessVariable<String> VAR_LOGMESSAGE = create("logMessage");
    public static final ProcessVariable<String> VAR_AMOUNT = create("amount");
    public static final ProcessVariable<String> VAR_REQUEST = create("request");
    public static final ProcessVariable<String> VAR_APPROVAL_TYPE = create("approvalType");



}
