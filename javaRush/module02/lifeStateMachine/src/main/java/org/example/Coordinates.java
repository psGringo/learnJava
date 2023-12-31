package org.example;

import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Coordinates {
    @Getter
    @Setter
    private int start;
    @Getter
    @Setter
    private int end;

    public void increase() {
        start++;
        end++;
    }

    public void decrease() {
        start--;
        end--;
    }
}
