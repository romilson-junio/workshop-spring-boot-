package com.romilson.workshopspringboot.services;

import com.romilson.workshopspringboot.domain.Address;
import com.romilson.workshopspringboot.domain.City;
import com.romilson.workshopspringboot.domain.Client;
import com.romilson.workshopspringboot.domain.enums.TypeClient;
import com.romilson.workshopspringboot.dto.ClientDTO;
import com.romilson.workshopspringboot.dto.ClientNewDTO;
import com.romilson.workshopspringboot.repositories.AddressRepository;
import com.romilson.workshopspringboot.repositories.ClientRepository;
import com.romilson.workshopspringboot.services.exceptions.DataIntegrityException;
import com.romilson.workshopspringboot.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    public ClientRepository clientRepository;

    @Autowired
    public AddressRepository addressRepository;

    public Client findById(Integer id){
        Optional<Client> category = clientRepository.findById(id);
        return category.orElseThrow(() ->
                new ObjectNotFoundException("Client not found for id: ".concat(String.valueOf(id)).concat(", Tipo: ").concat(Client.class.getName())));
    }

    @Transactional
    public Client insert(Client client){
        client.setId(null);
        client = clientRepository.save(client);
        addressRepository.saveAll(client.getAddress());
        return client;
    }

    public Client update(Integer id, Client client) {
        Client clientFromDB = this.findById(id);
        updateData(clientFromDB, client);
        return clientRepository.save(clientFromDB);
    }

    public void delete(Integer id) {
        this.findById(id);
        try{
            clientRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Cannot delete client with associated ordereds");
        }
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return clientRepository.findAll(pageRequest);
    }

    public Client fromDTO(ClientDTO dto){
        return new Client(dto.getId(), dto.getName(), dto.getEmail(), null, null);
    }

    public Client fromDTO(ClientNewDTO dto) {
        Client client = new Client(null,dto.getName(), dto.getEmail(), dto.getCpfCnpj(), TypeClient.toEnum(dto.getTypeClient()));
        City city = new City(dto.getCityId(), null, null);
        Address address = new Address(null, dto.getPublicPlace(), dto.getNumber(), dto.getComplement(), dto.getDistrict(), dto.getZipCode(), client, city);
        client.getAddress().add(address);
        client.getPhones().add(dto.getPhone1());
        if(Objects.nonNull(dto.getPhone2()) && !"".equals(dto.getPhone2())){
            client.getPhones().add(dto.getPhone2());
        }
        if(Objects.nonNull(dto.getPhone3()) && !"".equals(dto.getPhone3())){
            client.getPhones().add(dto.getPhone3());
        }
        return client;
    }

    private void updateData(Client clientFromDB, Client client) {
        clientFromDB.setName(client.getName());
        clientFromDB.setEmail(client.getEmail());
    }

}
