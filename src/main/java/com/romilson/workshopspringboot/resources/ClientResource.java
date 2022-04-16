package com.romilson.workshopspringboot.resources;

import com.romilson.workshopspringboot.domain.Category;
import com.romilson.workshopspringboot.domain.Client;
import com.romilson.workshopspringboot.dto.CategoryDTO;
import com.romilson.workshopspringboot.dto.ClientDTO;
import com.romilson.workshopspringboot.dto.ClientNewDTO;
import com.romilson.workshopspringboot.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value="/clientes")
public class ClientResource {

    @Autowired
    public ClientService clientService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Client> find(@PathVariable("id") Integer id){
        return ResponseEntity.ok().body(clientService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll(){
        return ResponseEntity.ok().body(
                clientService.findAll().stream().map(o -> new ClientDTO(o)).collect(Collectors.toList()));
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<ClientDTO>> findAllPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ){
        Page<Client> list = clientService.findPage(page,linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(list.map(o -> new ClientDTO(o)));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody ClientNewDTO dto){
        Client client = clientService.insert(clientService.fromDTO(dto));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ClientDTO clientDTO, @PathVariable Integer id){
        Client client = clientService.fromDTO(clientDTO);
        client = clientService.update(id, client);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
	
}
