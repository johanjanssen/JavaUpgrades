package com.example;

public enum ExampleEnum {
    TEST {
        public String retrieve() {
            return "test";
        }
    };

    public String getValue() {
        return "21";
    }
}