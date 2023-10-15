package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
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
