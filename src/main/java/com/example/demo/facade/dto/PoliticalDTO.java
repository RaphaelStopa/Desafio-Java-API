package com.example.demo.facade.dto;

import com.example.demo.domain.Address;
import com.example.demo.domain.LawProject;
import com.example.demo.domain.Phone;
import com.example.demo.domain.PoliticalParty;
import com.example.demo.domain.enumeration.ElectivePositionType;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsExclude;
import org.apache.commons.lang3.builder.HashCodeExclude;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@ToString
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@Getter
@Setter
public class PoliticalDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Size(max = 14)
    private String cpf;

    private ElectivePositionType electivePositionType;

    @NotNull
    private boolean isLeader;

    private Set<AddressWithoutPoliticianDTO> addresses;

    private Set<LawProjectWithoutPoliticianDTO> lawProjects;

    private Set<PhoneWithoutPoliticianDTO> phones;

    private PoliticalPartyDTO politicalParty;
}
