package ch.umb.solutions.consulting.camunda.springbootquickstarter.domain;

import java.io.Serializable;

public class Bar implements Serializable {
    Boolean bar;
    Integer size;

    public Bar() {
    }

    public Bar(Boolean bar, Integer size) {
        this.bar = bar;
        this.size = size;
    }

    public Boolean getBar() {
        return bar;
    }

    public void setBar(Boolean bar) {
        this.bar = bar;
    }

    @Override
    public String toString() {
        return "Bar{" +
               "bar=" + bar +
               "size=" + size +
               '}';
    }
}
