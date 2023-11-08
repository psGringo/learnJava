package org.example;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode
@RequiredArgsConstructor
@NoArgsConstructor
public class Person implements Serializable {
    @Getter
    @Setter
    @NonNull
    private String name;

    @Getter
    @Setter
    @JsonIgnore
    transient String secretProperty = "someSecretProperty";
}
