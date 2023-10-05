package org.example;

import lombok.SneakyThrows;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {

        var person = new Person();
        System.out.println(person.getName());
        System.out.println(person);

        System.out.println();

        var getterSetterExample = new LombokGetterSetterExample();
        getterSetterExample.setName("exampleName");
        System.out.println(getterSetterExample.getName());

        System.out.println();

        // allArgsContructor
        var constructorExample = new LombokConstructorExample<String>(1, 2, "this is description");
        var internalClassObject = new LombokConstructorExample.NoArgsExample();
        System.out.println(constructorExample);
        System.out.println(internalClassObject);

        // requiredConstructor
        var requiredConstructorExample = new LombokConstrutorExample2("hi", "there");
        System.out.println(requiredConstructorExample);

        System.out.println();

        var equalsAndHashCodeExample = new EqualsAndHashCodeExample("exampleName");
        var equalsAndHashCodeExample2 = new EqualsAndHashCodeExample("exampleName");
        System.out.println(equalsAndHashCodeExample.hashCode() == equalsAndHashCodeExample2.hashCode());
        System.out.println(equalsAndHashCodeExample.equals(equalsAndHashCodeExample2));

        System.out.println();

        var builderExamplePerson = LombokBuilderExamplePerson.builder().name("Stanley").family("Panteleev").build();
        System.out.println(builderExamplePerson);

        System.out.println();

        var annotations = MyAnnotationExample.class.getAnnotations();

        // annotation values
        var myAnnotation = MyAnnotationExample.class.getAnnotation(MyAnnotation.class);
        System.out.println(myAnnotation.name());
        System.out.println(myAnnotation.id());

        System.out.println();

        // get all annotations of class
        for (Annotation annotation : annotations) {
            var typeName = annotation.annotationType().getName();
            System.out.println(typeName);
            for (Method method : annotation.getClass().getDeclaredMethods()) {
                System.out.println(method.getName());
            }
        }

    }
}