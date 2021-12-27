package com.example.demo.facade.dto;


import com.example.demo.domain.enumeration.AddressType;
import lombok.*;

import java.io.Serializable;

@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@Getter
@Setter
public class AddressDTO implements Serializable {

    private Long id;

    private String street;

    private String number;

    private String zipcode;

    private String district;

    private String complement;

    private AddressType addressType;

    private CityDTO city;

    private PoliticalIdDTO political;
}
