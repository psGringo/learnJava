package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Position  {
    @Getter
    @Setter
    public Coordinates X;
    @Getter
    @Setter
    public Coordinates Y;
}


