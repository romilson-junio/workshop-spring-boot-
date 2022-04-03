package com.romilson.workshopspringboot.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@Embeddable
public class OrderedItemPK implements Serializable {

    private static final long serialVersionUID = 1L;
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Ordered ordered;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Product product;
}
