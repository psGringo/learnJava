package org.example;

import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Position  {
    @Getter
    @Setter
    public Coordinates X;
    @Getter
    @Setter
    public Coordinates Y;
}


