package com.example;

public class Student {
    final private String firstName;

    public Student(String firstName) {
        this.firstName = firstName;
    }

    boolean isBlankName() {
        return firstName == null || firstName.trim().isEmpty();
    }

    static String getInfo() {
        return "Java class";
    }
}
