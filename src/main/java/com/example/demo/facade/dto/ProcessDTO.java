package com.example.demo.facade.dto;

import com.example.demo.domain.Political;
import com.example.demo.domain.State;
import com.example.demo.domain.enumeration.ProcessStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@Getter
@Setter
public class ProcessDTO implements Serializable {

    private Long id;

    @NotNull
    private String number;

    @NotNull
    private String court;

    private String briefContext;

    private ProcessStatus processStatus;

    @NotNull
    private PoliticalIdDTO political;

    private StateDTO state;

}
