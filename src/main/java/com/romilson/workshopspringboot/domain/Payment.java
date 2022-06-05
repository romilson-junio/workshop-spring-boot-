package com.romilson.workshopspringboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.romilson.workshopspringboot.domain.enums.StatePayment;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Pagamento")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
public abstract class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include()
    @Getter @Setter
    private Integer id;

    private Integer statePayment;

    @OneToOne
    @JoinColumn(name = "ordered_id")
    @MapsId
    @Getter @Setter
    @JsonIgnore
    private Ordered ordered;

    public Payment(Integer id, StatePayment statePayment, Ordered ordered) {
        this.id = id;
        this.statePayment = (Objects.isNull(statePayment)) ? null : statePayment.getCode();
        this.ordered = ordered;
    }

    public StatePayment getStatePayment() {
        return StatePayment.toEnum(statePayment);
    }

    public void setStatePayment(StatePayment statePayment) {
        this.statePayment = statePayment.getCode();
    }
}
