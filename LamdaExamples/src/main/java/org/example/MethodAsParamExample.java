package org.example;

public class MethodAsParamExample {
    static void sayHello(MyFunctionalInterface<String> greeting) {
        System.out.println(greeting.get());
    }

    public static void execute() {
        sayHello(Helper::greeting);

        var helper = new Helper();
        sayHello(helper::anotherGreeting);
    }
}

class Helper {
    public static String greeting() {
        return "hi,there, from static method";
    }

    public String anotherGreeting() {
        return "hi,there, from non static method";
    }
}

