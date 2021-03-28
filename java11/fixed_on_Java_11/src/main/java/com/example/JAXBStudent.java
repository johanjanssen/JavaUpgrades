package com.example;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JAXBStudent {

    String name;

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public JAXBStudent() {
    }

    public JAXBStudent(String name) {
        this.name = name;
    }
}