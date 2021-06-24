package ch.umb.solutions.consulting.camunda.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Set;
import org.camunda.bpm.engine.delegate.VariableScope;
import org.camunda.spin.plugin.variable.SpinValues;

public class ProcessVariable<T> {


    private Class<T> typeParameterClass;

    private ObjectMapper jacksonObjectMapper = new ObjectMapper();

    private static Set<Class<?>> SIMPLE_CLASSES = Set.of(Boolean.class, Character.class, Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class, Void.class, String.class);

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
        if (typeParameterClass == null) {
            variableScope.setVariable(variableName, variableValue);
        } else {
            var serializedValue = jacksonObjectMapper.writeValueAsString(variableValue);
            var jsonValue = SpinValues.jsonValue(serializedValue);
            variableScope.setVariable(variableName, jsonValue);
        }
    }



}
