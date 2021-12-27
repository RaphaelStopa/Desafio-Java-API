package com.example.demo.facade.dto;

import com.example.demo.domain.enumeration.ElectivePositionType;
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
public class PoliticalToUpdateDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Size(max = 14)
    private String cpf;

    private ElectivePositionType electivePositionType;

    @NotNull
    private boolean isLeader;

    private PoliticalPartyDTO politicalParty;
}
