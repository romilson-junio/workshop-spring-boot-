package com.romilson.workshopspringboot.services;

import com.romilson.workshopspringboot.domain.Category;
import com.romilson.workshopspringboot.repositories.CategoryRepository;
import com.romilson.workshopspringboot.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    public CategoryRepository categoryRepository;

    public Category findById(Integer id){
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElseThrow(() ->
                new ObjectNotFoundException("Category not found for id: ".concat(String.valueOf(id)).concat(", Tipo: ").concat(Category.class.getName())));
    }

    public void save(Category category){
        categoryRepository.save(category);
    }
}
