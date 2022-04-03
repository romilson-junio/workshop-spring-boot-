package com.romilson.workshopspringboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.romilson.workshopspringboot.domain.enums.TypeClient;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "Cliente")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter @Setter
    @EqualsAndHashCode.Include()
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String cpfCnpj;

    private Integer typeClient;

    @Getter
    @OneToMany(mappedBy = "client")
    private List<Address> address = new ArrayList<>();

    @Getter
    @ElementCollection
    @CollectionTable(name = "telefone")
    private Set<String> phones = new HashSet<>();

    @Getter
    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Ordered> ordereds = new ArrayList<>();

    public Client(Integer id, String name, String email, String cpfCnpj, TypeClient typeClient) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpfCnpj = cpfCnpj;
        this.typeClient = typeClient.getCode();
    }

    public TypeClient getTypeClient() {
        return TypeClient.toEnum(typeClient);
    }

    public void setTypeClient(TypeClient typeClient) {
        this.typeClient = typeClient.getCode();
    }
}
