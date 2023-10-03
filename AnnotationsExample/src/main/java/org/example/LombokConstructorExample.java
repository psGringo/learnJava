package org.example;

import lombok.*;

@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class LombokConstructorExample<T> {
    private int x, y;
    @NonNull
    private T description;

    @NoArgsConstructor
    @ToString
    public static class NoArgsExample {
        @NonNull
        private String field;
    }
}
