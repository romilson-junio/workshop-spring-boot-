package com.romilson.workshopspringboot.repositories;

import com.romilson.workshopspringboot.domain.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedRepository extends JpaRepository<Ordered, Integer> {

}
