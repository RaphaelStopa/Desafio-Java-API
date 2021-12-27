package com.example.demo.facade.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@Getter
@Setter
public class StateDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 4, max = 32)
    private String name;

    @NotNull
    private String acronym;
}
