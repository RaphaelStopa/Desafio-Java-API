package com.example.demo.facade.dto;


import com.example.demo.domain.enumeration.ProcessStatus;
import lombok.*;

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

    private String number;

    private String court;

    private String briefContext;

    private ProcessStatus processStatus;

    private PoliticalIdDTO political;

    private StateDTO state;

}
