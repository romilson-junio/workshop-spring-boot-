package com.romilson.workshopspringboot.repositories;

import com.romilson.workshopspringboot.domain.OrderedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedItemRepository extends JpaRepository<OrderedItem, Integer> {

}
