package com.example;

public class Container {
    private ExampleEnum exampleEnum;
    public Container(ExampleEnum exampleEnum) {
        this.exampleEnum = exampleEnum;
    }

    public String retrieveValue() {
        return exampleEnum.getValue();
    }
}
