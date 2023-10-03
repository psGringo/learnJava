package org.example;

public class CounterStateMachineImpl implements CounterStateMachine {

    private CounterState counterState;

    public CounterStateMachineImpl() {
        this.counterState = CounterState.EnterCommand;
    }

    @Override
    public CounterState getCurrentState() {
        return counterState;
    }

    @Override
    public void nextState(CounterState state) {
        counterState = state;
    }
}
