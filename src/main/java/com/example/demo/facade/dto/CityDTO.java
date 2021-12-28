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
public class CityDTO implements Serializable {

    private Long id;

    private String name;

    private StateDTO state;
}
