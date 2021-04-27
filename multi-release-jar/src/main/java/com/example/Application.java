package com.example;

public class Application {

    public static void main(String[] args) {
        System.out.println("Java version " + System.getProperty("java.version"));
        System.out.println("Implementation " + Student.getInfo());

        Student studentBlank = new Student(" ");
        System.out.println("Student name with one space is blank: " + studentBlank.isBlankName());

        Student student = new Student("James");
        System.out.println("Student name with one space is blank: " + student.isBlankName());
    }
}
