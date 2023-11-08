package org.example;

import java.io.*;

public class Main {

    static final String fileName = "PersonSerialized";

    public static void main(String[] args) {
        DefaultSerialization.test();
        JacksonObjectMapperJson.testJson();
        JacksonObjectMapperJson.testXml();
    }

}