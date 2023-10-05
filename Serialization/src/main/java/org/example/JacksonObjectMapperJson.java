package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;


import java.io.File;
import java.io.IOException;

public class JacksonObjectMapperJson {
    public static void testJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        var person = new Person("Stanley");
        try {
            var file = new File("person.json");
            objectMapper.writeValue(file, person);
            var anotherPerson = objectMapper.readValue(file, Person.class);
            System.out.println(person.equals(anotherPerson));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void testXml() {
        XmlMapper mapper = new XmlMapper();
        var person = new Person("Stanley");
        try {
            var file = new File("person.xml");
            mapper.writeValue(file, person);
            var anotherPerson = mapper.readValue(file, Person.class);
            System.out.println(person.equals(anotherPerson));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
