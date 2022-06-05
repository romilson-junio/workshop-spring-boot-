package com.romilson.workshopspringboot.services;

import com.romilson.workshopspringboot.domain.TicketPayment;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class TicketService {

    public void fillPaymentWithTicket(TicketPayment payment, Date dateOrdered){
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateOrdered);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        payment.setDueDate(cal.getTime());
    }

}
