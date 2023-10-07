package org.example;

public class Main {
    public static void main(String[] args) {
        var cat = new Animal() {
            @Override
            void sayHello() {
                System.out.println("meow");
            }
        };

        cat.sayHello();
    }
}

class Animal {
    void sayHello() {
    }

    ;
}