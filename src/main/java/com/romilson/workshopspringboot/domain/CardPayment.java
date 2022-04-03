package com.romilson.workshopspringboot.domain;

import com.romilson.workshopspringboot.domain.enums.StatePayment;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class CardPayment extends Payment{

    private static final long serialVersionUID = 1L;

    private Integer installmentsNumber;

    public CardPayment(){
        super();
    }

    public CardPayment(Integer id, StatePayment statePayment, Ordered ordered, Integer installmentsNumber) {
        super(id, statePayment, ordered);
        this.installmentsNumber = installmentsNumber;
    }
}
