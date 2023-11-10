package org.example;

import java.io.Serializable;

public class CounterImpl implements Counter, Serializable {

    private int count;

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public void setCount(int value) {
        count = value;
    }

    @Override
    public void increase() {
        count++;
    }

    @Override
    public void reset() {
        count = 0;
    }
}
