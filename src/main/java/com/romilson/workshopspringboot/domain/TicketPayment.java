package com.romilson.workshopspringboot.domain;

import com.romilson.workshopspringboot.domain.enums.StatePayment;
import lombok.Data;

import javax.persistence.Entity;
import java.util.Date;

@Data
@Entity
public class TicketPayment extends Payment {

    private static final long serialVersionUID = 1L;

    private Date dueDate;
    private Date paymentDate;

    public TicketPayment() {
        super();
    }

    public TicketPayment(Integer id, StatePayment statePayment, Ordered ordered, Date dueDate, Date paymentDate) {
        super(id, statePayment, ordered);
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
    }
}
