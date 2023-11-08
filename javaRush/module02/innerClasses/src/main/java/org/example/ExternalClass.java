package org.example;

public class ExternalClass {

    public InternalClass getInternalClass() {
        return new InternalClass();
    }

    public class InternalClass {

        private String name = "Hello, there, this is name in internal class";

        public static void sayHelloStatic() {
            System.out.println("hello from static method");
        }

        public void sayHello() {
            System.out.println(this.name);
        }
    }

    public static class InternalStaticClass {
        public static void sayHello() {
            System.out.println("this is the static method");
        }
        public void sayHelloNonStatic() {
            System.out.println("this is the usual method");
        }
    }
}
