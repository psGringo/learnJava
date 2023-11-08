package org.example;

public class ConstructorExample {
    public static void execute() {
        ConstructorWithName constructor = Person::new;
        Person person = constructor.create("Stanley");
        System.out.println(person.getName());

        ConstructorWithNameAndSkills constructorWithNameAndSkills = Person::new;
        var anotherPerson = constructorWithNameAndSkills.create("Stanley", 100);
        System.out.println(anotherPerson.getName());
    }
}

interface ConstructorWithName {
    Person create(String name);
}

interface ConstructorWithNameAndSkills {
    Person create(String name, int skills);
}

class Person {
    private String name;
    private int skills;

    public int getSkills() {
        return skills;
    }

    public String getName() {
        return name;
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, int skills) {
        this.name = name;
        this.skills = skills;
    }
}
