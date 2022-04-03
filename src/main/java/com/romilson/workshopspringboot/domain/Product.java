package com.romilson.workshopspringboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor()
@NoArgsConstructor
@Entity
@Table(name = "produto")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include()
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private Double price;

    @ManyToMany()
    @JoinTable(
            name = "produto_categoria",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )

    @JsonIgnore
    @Getter
    private List<Category> categories = new ArrayList<>();

    @Getter
    @OneToMany(mappedBy = "id.product")
    @JsonIgnore
    private Set<OrderedItem> items = new HashSet<>();

    public Product(Integer id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @JsonIgnore
    public List<Ordered> getOrdereds(){
        List<Ordered> list = new ArrayList<>();
        for (OrderedItem x: items) {
            list.add(x.getOrdered());
        }
        return list;
    }
}
