package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

public class DefaultSerialization {

    static final String fileName = "PersonSerialized";

    @Test
    public static void test() {
        Person person = new Person("123");
        Serialize(person);
        Person anotherPerson = Deserialize();
        Assertions.assertEquals(person.hashCode(), anotherPerson.hashCode());
    }

    static void Serialize(Person person) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            objectOutputStream.writeObject(person);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static Person Deserialize() {
        try (ObjectInputStream objectInputnStream = new ObjectInputStream(new FileInputStream(fileName))) {

            return (Person) objectInputnStream.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}