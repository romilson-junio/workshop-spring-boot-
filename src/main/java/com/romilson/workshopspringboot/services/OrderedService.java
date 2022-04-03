package com.romilson.workshopspringboot.services;

import com.romilson.workshopspringboot.domain.Category;
import com.romilson.workshopspringboot.domain.Ordered;
import com.romilson.workshopspringboot.repositories.CategoryRepository;
import com.romilson.workshopspringboot.repositories.OrderedRepository;
import com.romilson.workshopspringboot.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderedService {

    @Autowired
    public OrderedRepository orderedRepository;

    public Ordered findById(Integer id){
        Optional<Ordered> ordered = orderedRepository.findById(id);
        return ordered.orElseThrow(() ->
                new ObjectNotFoundException("Ordered not found for id: ".concat(String.valueOf(id)).concat(", Tipo: ").concat(Ordered.class.getName())));
    }

    public void save(Ordered ordered){
        orderedRepository.save(ordered);
    }
}
