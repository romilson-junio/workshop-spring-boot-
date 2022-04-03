package com.romilson.workshopspringboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Estado")
public class State implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter @Setter
    @EqualsAndHashCode.Include()
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Getter @Setter
    private String name;

    private String acronym;

    @Getter
    @OneToMany(mappedBy = "state")
    @JsonIgnore
    private List<City> cities = new ArrayList<>();

    public State(Integer id, String name, String acronym) {
        this.id = id;
        this.name = name;
        this.acronym = acronym;
    }
}
