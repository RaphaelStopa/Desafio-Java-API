package com.example.demo.facade.dto;

import com.example.demo.domain.State;
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
public class CityDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private StateDTO state;
}