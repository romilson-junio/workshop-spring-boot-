package com.romilson.workshopspringboot.resources;

import com.romilson.workshopspringboot.services.OrderedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/pedidos")
public class OrderedResource {

    @Autowired
    public OrderedService orderedService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getPedido(@PathVariable("id") Integer id){
        return ResponseEntity.ok().body(orderedService.findById(id));
    }
	
}
