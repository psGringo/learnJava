package org.example;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        printFields();
        printMethods();
        getModifiers();
        getPackage();
        getInterfaces();
    }

    static void getInterfaces() {
        System.out.printf("%nGet interfaces %n");

        for (Class<?> anInterface : LocalDateTime.class.getInterfaces()) {
            System.out.println(anInterface.getName());
            System.out.println(anInterface.getCanonicalName());
            System.out.println(anInterface.getTypeName());
            System.out.println(anInterface.getSimpleName());
        }
    }

    static void getPackage() {
        System.out.printf("%nGet Package Example %n");
        System.out.println(Main.class.getPackage().getName());
    }

    static void getModifiers() {
        System.out.printf("%nGet Modifiers Example %n");
        int modifiers = LocalDateTime.class.getModifiers();
        System.out.printf("isPublic %s%n", Modifier.isPublic(modifiers));
        System.out.printf("isPrivate %s%n", Modifier.isPrivate(modifiers));
        System.out.printf("isFinal %s%n", Modifier.isFinal(modifiers));
        System.out.printf("isAbstract %s%n", Modifier.isAbstract(modifiers));
    }

    static void printFields() {
        System.out.printf("%nFields%n%n");
        printFieldNames(LocalDateTime.class.getFields());
        System.out.printf("%nDeclaredFields%n%n");
        printFieldNames(LocalDateTime.class.getDeclaredFields());
    }

    static void printMethods() {
        System.out.printf("%nMethods%n%n");
        printMethodNames(Number.class.getMethods());
        System.out.printf("%nDeclared methods%n%n");
        printMethodNames(Number.class.getDeclaredMethods());
    }

    static void printFieldNames(Field[] fields) {
        for (Field field : fields) {
            System.out.println(field.getName());
        }
    }

    static void printMethodNames(Method[] methods) {
        var list = Arrays.stream(methods).map(Method::getName).collect(Collectors.toList());
        System.out.println(list);
//        alternative
//        for (Method method : methods) {
//            System.out.println(method.getName());
//        }
    }

    /*
     *  example how to get class
     * */
    static void gettingClassExample() {
        try {
            Class<?> personClass = Class.forName("org.example.Person");
            personClass = Person.class;

            var person = new Person();
            personClass = person.getClass();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}

