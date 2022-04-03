package com.romilson.workshopspringboot.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.romilson.workshopspringboot.domain.enums.StatePayment;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "Pagamento_Boleto")
public class TicketPayment extends Payment {

    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dueDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
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
