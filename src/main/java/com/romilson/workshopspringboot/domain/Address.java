package com.romilson.workshopspringboot.domain;

import com.fasterxml.jackson.annotation. JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Endereco")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include()
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String publicPlace;
    private String number;
    private String complement;
    private String district;
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private Client client;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

}
