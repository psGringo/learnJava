package org.example;

public class AssigningExample {
    public static void execute() {
        MyFunctionalInterface<String> greeting = () -> "hi, there";
        MyFunctionalInterface<Integer> zero = () -> 0;
        MyFunctionalInterface<Double> pi = () -> 3.14;

        System.out.println(greeting.get());
        System.out.println(zero.get());
        System.out.println(pi.get());
    }
}
