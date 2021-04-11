package com.example.bind;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

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