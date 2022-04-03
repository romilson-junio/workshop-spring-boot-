package com.romilson.workshopspringboot.services;

import com.romilson.workshopspringboot.domain.Client;
import com.romilson.workshopspringboot.repositories.ClientRepository;
import com.romilson.workshopspringboot.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    public ClientRepository clientRepository;

    public Client findById(Integer id){
        Optional<Client> category = clientRepository.findById(id);
        return category.orElseThrow(() ->
                new ObjectNotFoundException("Client not found for id: ".concat(String.valueOf(id)).concat(", Tipo: ").concat(Client.class.getName())));
    }

    public void save(Client client){
        clientRepository.save(client);
    }
}
