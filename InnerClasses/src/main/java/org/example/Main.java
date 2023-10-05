package org.example;

public class Main {
    public static void main(String[] args) {

        var internal = new ExternalClass().new InternalClass();
        internal.sayHello();

        // static class and method
        ExternalClass.InternalStaticClass.sayHello();
        // class and static method
        ExternalClass.InternalClass.sayHelloStatic();

        var internal2 = new ExternalClass().getInternalClass();
        internal2.sayHello();

        // creation of static class as usual class
        var internal3 = new ExternalClass.InternalStaticClass();
        internal3.sayHelloNonStatic();
    }
}