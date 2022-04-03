package com.romilson.workshopspringboot.repositories;

import com.romilson.workshopspringboot.domain.City;
import com.romilson.workshopspringboot.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

}
