package com.romilson.workshopspringboot.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "Pedido")
public class Ordered implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include()
    @Getter @Setter
    private Integer id;

    @Getter @Setter
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date instant;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "ordered")
    @Getter @Setter
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @Getter @Setter
    private Client client;

    @ManyToOne
    @JoinColumn(name = "delivery_address_id")
    @Getter @Setter
    private Address deliveryAddress;

    @Getter
    @OneToMany(mappedBy = "id.ordered")
    private Set<OrderedItem> items = new HashSet<>();

    public Ordered(Integer id, Date instant, Client client, Address deliveryAddress) {
        this.id = id;
        this.instant = instant;
        this.client = client;
        this.deliveryAddress = deliveryAddress;
    }
    
    public Double getTotalValue(){
        double soma = 0D;
        for (OrderedItem item: items ) {
            soma += item.getSubTotal();
        }
        return soma;
    }
}
