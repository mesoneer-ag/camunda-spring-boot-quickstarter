package ch.umb.solutions.consulting.camundaspringbootquickstarter;

import ch.umb.solutions.camunda.helper.ProcessVariable;

import static ch.umb.solutions.camunda.helper.ProcessVariable.create;

public class ProcessVariables {
    public static final ProcessVariable<String> VAR_SAMPLE = create("sampleVar");
    public static final ProcessVariable<String> VAR_LOGMESSAGE = create("logMessage");
    public static final ProcessVariable<String> VAR_AMOUNT = create("amount");


}
