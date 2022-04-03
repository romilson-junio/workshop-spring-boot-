package com.romilson.workshopspringboot.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Item_Pedido")
public class OrderedItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include()
    @EmbeddedId
    @JsonIgnore
    private OrderedItemPK id = new OrderedItemPK();

    private Double discount;
    private Integer quantity;
    private Double price;

    public OrderedItem(Ordered ordered, Product product, Double discount, Integer quantity, Double price) {
        super();
        this.id.setOrdered(ordered);
        this.id.setProduct(product);
        this.discount = discount;
        this.quantity = quantity;
        this.price = price;
    }
    @JsonIgnore
    public Ordered getOrdered(){
        return id.getOrdered();
    }

    public Product getProduct(){
        return id.getProduct();
    }
}
