package org.example;

import org.example.commands.Command;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static CounterStateMachine counterStateMachine = new CounterStateMachineImpl();
    static HashMap<String, Command> commands = new HashMap<>();

    static Path counterFilePath = Path.of("counter.data");

    static Scanner scanner;

    static Counter counter;

    public static void main(String[] args) {

        try {
            init();

            while (true) {
                CounterState currentState = Main.counterStateMachine.getCurrentState();
                if (currentState.equals(CounterState.STOP)) {
                    stop();
                    break;
                } else if (currentState.equals(CounterState.EnterCommand)) {
                    enterCommand();
                }
            }
        } catch (RuntimeException e) {
            System.out.println(String.format("Произошла ошибка %s", e.getMessage()));
        }

    }

    static void enterCommand() {
        String commandText = scanner.nextLine().trim();
        Command command = commands.get(commandText);
        if (command != null)
            command.execute();
        else
            System.out.println("Команда не распознана, попробуйте ещё раз, возможные команды /inc, /stop, /reset");
    }

    static void init() {
        counter = Utils.tryDeserialize(counterFilePath);
        Utils.addCommands(counter);
        scanner = new Scanner(System.in);
        printWelcomeMessage();
    }

    static void printWelcomeMessage() {
        printCurrentCount();
        System.out.println("Введите команду( /inc, /stop, /reset\"):");
    }

    static void printCurrentCount() {
        System.out.println(String.format(Consts.CurrentCountValue, counter.getCount()));
    }

    static void stop() {
        Utils.Serialize(counter, counterFilePath);
        printCurrentCount();
        System.out.println("Успешный выход из программы");
    }

}
