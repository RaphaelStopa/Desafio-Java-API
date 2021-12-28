package com.example.demo.facade.dto;

import lombok.*;

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

    private String name;

    private String acronym;
}
