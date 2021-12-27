package com.example.demo.facade.dto;

import com.example.demo.domain.enumeration.ElectivePositionType;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@ToString
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@Getter
@Setter
public class PoliticalForUserDTO implements Serializable {

    private Long id;

    private String name;

    private ElectivePositionType electivePositionType;

    private boolean isLeader;

    private Set<LawProjectWithoutPoliticianDTO> lawProjects;

    private PoliticalPartyDTO politicalParty;
}
