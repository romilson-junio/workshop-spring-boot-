package com.romilson.workshopspringboot.services;

import com.romilson.workshopspringboot.domain.Category;
import com.romilson.workshopspringboot.domain.Ordered;
import com.romilson.workshopspringboot.domain.OrderedItem;
import com.romilson.workshopspringboot.domain.TicketPayment;
import com.romilson.workshopspringboot.domain.enums.StatePayment;
import com.romilson.workshopspringboot.repositories.CategoryRepository;
import com.romilson.workshopspringboot.repositories.OrderedItemRepository;
import com.romilson.workshopspringboot.repositories.OrderedRepository;
import com.romilson.workshopspringboot.repositories.PaymentRepository;
import com.romilson.workshopspringboot.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class OrderedService {

    @Autowired
    public OrderedRepository orderedRepository;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderedItemRepository orderedItemRepository;

    public Ordered findById(Integer id){
        Optional<Ordered> ordered = orderedRepository.findById(id);
        return ordered.orElseThrow(() ->
                new ObjectNotFoundException("Ordered not found for id: ".concat(String.valueOf(id)).concat(", Tipo: ").concat(Ordered.class.getName())));
    }

    public void save(Ordered ordered){
        orderedRepository.save(ordered);
    }

    public Ordered insert(Ordered ordered) {
        ordered.setId(null);
        ordered.setInstant(new Date());
        ordered.getPayment().setStatePayment(StatePayment.PENDING);
        ordered.getPayment().setOrdered(ordered);
        if(ordered.getPayment() instanceof TicketPayment){
            TicketPayment payment = (TicketPayment) ordered.getPayment();
            ticketService.fillPaymentWithTicket(payment, ordered.getInstant());
        }
        ordered = orderedRepository.save(ordered);
        paymentRepository.save(ordered.getPayment());
        for (OrderedItem item: ordered.getItems()){
            item.setDiscount(0.0);
            item.setPrice(productService.findById(item.getProduct().getId()).getPrice());
            item.setOrdered(ordered);
        }
        orderedItemRepository.saveAll(ordered.getItems());
        return ordered;
    }
}
