package com.romilson.workshopspringboot.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Cidade")
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include()
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;
}
