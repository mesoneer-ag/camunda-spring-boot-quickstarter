package ch.umb.solutions.consulting.camunda.springbootquickstarter.domain;

import java.io.Serializable;


public class Foo implements Serializable {
    String someString;
    Integer someInteger;
    Bar bar;

    public Foo() {}

    public Foo(String someString, Integer someInteger, Bar bar) {
        this.someString = someString;
        this.someInteger = someInteger;
        this.bar = bar;
    }

    public String getSomeString() {
        return someString;
    }

    public void setSomeString(String someString) {
        this.someString = someString;
    }

    public Integer getSomeInteger() {
        return someInteger;
    }

    public void setSomeInteger(Integer someInteger) {
        this.someInteger = someInteger;
    }

    public Bar getBar() {
        return bar;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }

    @Override
    public String toString() {
        return "Foo{" +
               "someString='" + someString + '\'' +
               ", someInteger=" + someInteger +
               ", bar=" + bar +
               '}';
    }
}
