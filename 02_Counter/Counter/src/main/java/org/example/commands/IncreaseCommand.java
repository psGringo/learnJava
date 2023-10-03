package org.example.commands;

import org.example.Counter;
import org.example.CounterState;
import org.example.CounterStateMachine;

public class IncreaseCommand extends Command {

    private final String name = "/inc";

    public IncreaseCommand(CounterStateMachine stateMachine, Counter counter) {
        super(stateMachine, counter);
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public void execute() {
        getStateMachine().nextState(CounterState.EnterCommand);
        getCounter().increase();
        System.out.println(String.format("Текущее значение счётчика увеличилось и равно %d", getCounter().getCount()));
    }

}
