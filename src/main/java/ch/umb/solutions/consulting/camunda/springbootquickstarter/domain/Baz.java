package ch.umb.solutions.consulting.camunda.springbootquickstarter.domain;

public enum Baz {
    ONE("1"),
    TWO("2"),
    THREE("3");

    public final String number;

    Baz(String number) {
        this.number = number;
    }
}
