package ch.umb.solutions.consulting.camunda.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.delegate.VariableScope;
import org.camunda.spin.plugin.variable.SpinValues;

public class ProcessVariable<T> {

    private Class<T> typeParameterClass;

    private ObjectMapper jacksonObjectMapper = new ObjectMapper();

    private String variableName;

    private ProcessVariable(String variableName, Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
        this.variableName = variableName;
    }

    private ProcessVariable(String variableName) {
        this.variableName = variableName;
    }

    public static ProcessVariable create(String variableName) {
        return new ProcessVariable(variableName);
    }

    public static ProcessVariable createAsJson(String variableName, Class typeParameterClass) {
        return new ProcessVariable(variableName, typeParameterClass);
    }

    public String getVariableName() {
        return variableName;
    }

    public T get(VariableScope variableScope) {
        Object variable =  variableScope.getVariable(variableName);
        if (typeParameterClass == null) {
            return (T) variable;
        } else {
            try {
                return jacksonObjectMapper.readValue(variable.toString(), typeParameterClass);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public void set(VariableScope variableScope, T variableValue) throws JsonProcessingException {
        /**
         *  for enums it' seems it might be a little bit more complicated
         *  if we directly save the enum the bpmn seems to work fine:
         *
         *  --> ${baz2 == "TWO"} works fine as gateway condition
         *  --> ${baz == "TWO"} returns always false
         *
         *  therefore we should not save enums as JSON.
         *
         *  if we do something like this: execution.setVariable("approvalType", "AUTO_APPROVED") within the bpmn, the approvalType is obviously saved as String
         *  therefore we can not cast it back to an enum within a delegate..
         *
         *
         *  a possible solution would be to check if the typeParameterClass is an enum
         *  if so, save it normally, otherwise keep it the way it is...
         *
         *  if we get the variable now, we should check again if the typeParameterClass is an enum.
         *  if so, either directly cast it to the enum type, or (if it's saved as String), manually get the corresponding enum type (Enum.valueOf ...)
         *
         */

        if (typeParameterClass == null) {
            variableScope.setVariable(variableName, variableValue);
        } else {
            var serializedValue = jacksonObjectMapper.writeValueAsString(variableValue);
            var jsonValue = SpinValues.jsonValue(serializedValue).create();
            variableScope.setVariable(variableName, jsonValue);
        }
    }



}
