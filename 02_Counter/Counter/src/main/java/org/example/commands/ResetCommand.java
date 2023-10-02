package org.example.commands;

import org.example.Counter;
import org.example.CounterState;
import org.example.CounterStateMachine;

public class ResetCommand extends Command {

    private final String name = "/reset";

    public ResetCommand(CounterStateMachine stateMachine, Counter counter) {
        super(stateMachine, counter);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void execute() {
        getStateMachine().nextState(CounterState.EnterCommand);
        getCounter().reset();
        System.out.printf("Текущее значение счётчика сбросилось и равно %d%n", getCounter().getCount());
    }

}
