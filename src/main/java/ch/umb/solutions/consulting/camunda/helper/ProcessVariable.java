package ch.umb.solutions.consulting.camunda.helper;

import org.camunda.bpm.engine.delegate.VariableScope;

public class ProcessVariable<T> {

    private String variableName;

    private ProcessVariable(String variableName) {
        this.variableName = variableName;
    }

    public String getVariableName() {
        return variableName;
    }

    public T get(VariableScope variableScope) {

        return (T) variableScope.getVariable((variableName));
    }

    public void set(VariableScope variableScope, T variableValue) {
        variableScope.setVariable(variableName, variableValue);
    }

    public static ProcessVariable create(String variableName) {
        return new ProcessVariable(variableName);
    }

}
