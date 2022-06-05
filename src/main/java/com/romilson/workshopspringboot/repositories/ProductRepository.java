package com.romilson.workshopspringboot.repositories;

import com.romilson.workshopspringboot.domain.Category;
import com.romilson.workshopspringboot.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Transactional(readOnly = true)
    @Query("select distinct obj from Product obj inner join obj.categories cat where obj.name like %:name% and cat in :categories")
    Page<Product> search(@Param("name") String name, @Param("categories") List<Category> categories, Pageable pageRequest);

    Page<Product> findDistinctByNameContainingAndCategoriesIn(String name, List<Category> categories, Pageable pageRequest);
}
