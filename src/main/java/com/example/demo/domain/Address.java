package com.example.demo.domain;

import com.example.demo.domain.enumeration.AddressType;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Entity
@Table(name = "address")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@Getter
@Setter
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "street")
    private String street;

    @Size(max = 6)
    @Column(name = "number")
    private String number;

    @Column(name = "zipcode")
    private String zipcode;

    @Column(name = "district")
    private String district;

    @Column(name = "complement")
    private String complement;

    @Enumerated(EnumType.STRING)
    @Column(name = "address_type")
    private AddressType addressType;

    @NotNull
    @ManyToOne
    private City city;

    @NotNull
    @ManyToOne
    @EqualsAndHashCode.Exclude
    private Political political;

}
