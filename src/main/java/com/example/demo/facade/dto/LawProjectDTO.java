package com.example.demo.facade.dto;

import com.example.demo.domain.Political;
import com.example.demo.domain.enumeration.LawProjectType;
import lombok.*;

import javax.persistence.*;
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
public class LawProjectDTO implements Serializable {

    private Long id;

    @NotNull
    private String number;

    @NotNull
    @Size(min = 4, max = 4)
    private Integer year;

    private String text;

    private String justification;

    private LawProjectType lawProjectType;

    @NotNull
    private PoliticalDTO political;
}
