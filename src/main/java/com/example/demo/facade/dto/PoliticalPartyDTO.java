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
public class PoliticalPartyDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @Size(max = 10)
    private String acronym;
}
