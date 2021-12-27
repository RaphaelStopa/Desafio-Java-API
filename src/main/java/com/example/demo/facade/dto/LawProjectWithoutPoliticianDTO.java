package com.example.demo.facade.dto;

import com.example.demo.domain.enumeration.LawProjectType;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ToString
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@Getter
@Setter
public class LawProjectWithoutPoliticianDTO implements Serializable {

    private Long id;

    @NotNull
    private String number;

    @NotNull
    @Size(min = 4, max = 4)
    private Integer year;

    private String text;

    private String justification;

    private LawProjectType lawProjectType;
}
