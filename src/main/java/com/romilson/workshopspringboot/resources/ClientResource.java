package com.romilson.workshopspringboot.resources;

import com.romilson.workshopspringboot.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/clientes")
public class ClientResource {

    @Autowired
    public ClientService clientService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCliente(@PathVariable("id") Integer id){
        return ResponseEntity.ok().body(clientService.findById(id));
    }
	
}
