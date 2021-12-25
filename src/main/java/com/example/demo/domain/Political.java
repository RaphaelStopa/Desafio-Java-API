package com.example.demo.domain;

import com.example.demo.domain.enumeration.ElectivePositionType;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "political")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@Getter
@Setter
public class Political implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Size(max = 14)
    @CPF
    @Column(name = "cpf")
    private String cpf;

    @Enumerated(EnumType.STRING)
    @Column(name = "elective_position_type")
    private ElectivePositionType electivePositionType;

    @NotNull
    @Column(nullable = false)
    private boolean isLeader = false;

    @OneToMany(mappedBy = "political")
    private Set<Address> addresses = new HashSet<>();

    @OneToMany(mappedBy = "political")
    private Set<LawProject> lawProjects = new HashSet<>();

    @OneToMany(mappedBy = "political")
    @EqualsAndHashCode.Exclude
    private Set<Phone> phones = new HashSet<>();

    @ManyToOne
    private PoliticalParty politicalParty;

    @NotNull
    @Column(nullable = false)
    private boolean deleted = false;

    @OneToMany(mappedBy = "political")
    private Set<Photo> photos = new HashSet<>();
}
