package org.example.commands;

import org.example.Counter;
import org.example.CounterState;
import org.example.CounterStateMachine;

public class StopCommand extends Command {

    private final String name = "/stop";

    public StopCommand(CounterStateMachine stateMachine, Counter counter) {
        super(stateMachine, counter);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void execute() {
        getStateMachine().nextState(CounterState.STOP);
    }

}
