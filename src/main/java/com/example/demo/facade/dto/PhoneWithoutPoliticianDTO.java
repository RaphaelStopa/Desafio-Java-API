package com.example.demo.facade.dto;

import com.example.demo.domain.enumeration.PhoneType;
import com.example.demo.domain.enumeration.UseType;
import lombok.*;

import java.io.Serializable;

@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@Getter
@Setter
public class PhoneWithoutPoliticianDTO implements Serializable {

    private Long id;

    private String number;

    private PhoneType phoneType;

    private UseType useType;
}
