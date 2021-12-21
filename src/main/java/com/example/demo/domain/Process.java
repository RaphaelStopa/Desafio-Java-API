package com.example.demo.domain;


import com.example.demo.domain.enumeration.ProcessStatus;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Table(name = "process")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@Getter
@Setter
public class Process implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "number")
    private String number;

    @NotNull
    @Column(name = "court")
    private String court;

    @Column(name = "brief_context")
    private String briefContext;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProcessStatus processStatus;

    @NotNull
    @ManyToOne
    private Political political;

    @ManyToOne
    private State state;

}
