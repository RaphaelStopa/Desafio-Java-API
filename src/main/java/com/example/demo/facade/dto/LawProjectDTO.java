package com.example.demo.facade.dto;

import com.example.demo.domain.enumeration.LawProjectType;
import lombok.*;

import java.io.Serializable;

@ToString
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@Getter
@Setter
public class LawProjectDTO implements Serializable {

    private Long id;

    private String number;

    private Integer year;

    private String text;

    private String justification;

    private LawProjectType lawProjectType;

    private PoliticalIdDTO political;
}
