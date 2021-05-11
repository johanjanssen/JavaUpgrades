package com.example;

public record Student(String firstName) {

    boolean isBlankName() {
        return firstName.isBlank();
    }

    static String getInfo() {
        return "Java record";
    }
}
