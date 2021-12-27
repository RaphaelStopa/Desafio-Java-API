package com.example.demo.facade.dto;

import com.example.demo.domain.enumeration.PhoneType;
import com.example.demo.domain.enumeration.UseType;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@Getter
@Setter
public class PhoneDTO implements Serializable {

    private Long id;

    private String number;

    private PhoneType phoneType;

    private UseType useType;

    private PoliticalIdDTO political;
}
