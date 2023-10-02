package org.example;

public interface CounterStateMachine {

    CounterState getCurrentState();

    void nextState(CounterState state);

}
