package com.example.demo.domain;

import com.example.demo.domain.enumeration.LawProjectType;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Table(name = "law_project")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@Builder(toBuilder = true)
@Getter
@Setter
public class LawProject implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "number")
    private String number;

    @NotNull
    @Column(name = "year")
    private Integer year;

    @Lob
    @Column(name = "text")
    private String text;

    @Lob
    @Column(name = "justification")
    private String justification;

    @Enumerated(EnumType.STRING)
    @Column(name = "law_project_type")
    private LawProjectType lawProjectType;

    @NotNull
    @ManyToOne
    @EqualsAndHashCode.Exclude
    private Political political;

}
