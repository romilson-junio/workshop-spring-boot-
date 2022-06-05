package com.romilson.workshopspringboot.resources;

import com.romilson.workshopspringboot.domain.Ordered;
import com.romilson.workshopspringboot.services.OrderedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value="/pedidos")
public class OrderedResource {

    @Autowired
    public OrderedService orderedService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Ordered> find(@PathVariable("id") Integer id){
        return ResponseEntity.ok().body(orderedService.findById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Ordered ordered){
        ordered = orderedService.insert(ordered);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(ordered.getId()).toUri();
        return  ResponseEntity.created(uri).build();
    }
	
}
