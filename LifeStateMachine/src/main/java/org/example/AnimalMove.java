package org.example;

import java.io.IOException;

@FunctionalInterface
public interface AnimalMove {
    void execute() throws IOException;
}
