package com.example.bind;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.StringWriter;

public class JAXBService {
    public String convertToXML() throws JAXBException {
        JAXBStudent jaxbStudent = new JAXBStudent("Mike");

        JAXBContext context = JAXBContext.newInstance(JAXBStudent.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(jaxbStudent, stringWriter );
        return stringWriter.toString();
    }
}
