package org.example;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;


@RequiredArgsConstructor
@ToString
public class LombokConstrutorExample2 {
    private String someField;
    private @NonNull String someRequiredField;
    private final String anotherRequiredField;
}
