package com.romilson.workshopspringboot.services;

import com.romilson.workshopspringboot.domain.Category;
import com.romilson.workshopspringboot.dto.CategoryDTO;
import com.romilson.workshopspringboot.repositories.CategoryRepository;
import com.romilson.workshopspringboot.services.exceptions.DataIntegrityException;
import com.romilson.workshopspringboot.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category findById(Integer id){
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElseThrow(() ->
                new ObjectNotFoundException("Category not found for id: ".concat(String.valueOf(id)).concat(", Tipo: ").concat(Category.class.getName())));
    }

    public Category insert(Category category){
        category.setId(null);
        return categoryRepository.save(category);
    }

    public Category update(Integer id, Category category) {
        Category categoryFromDB = this.findById(id);
        updateData(categoryFromDB, category);
        return categoryRepository.save(categoryFromDB);
    }

    public void delete(Integer id) {
        Category category = this.findById(id);
        try{
            categoryRepository.delete(category);
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Cannot delete category with associated products");
        }

    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return categoryRepository.findAll(pageRequest);
    }

    public Category fromDTO(CategoryDTO dto){
        return new Category(dto.getId(), dto.getName());
    }

    private void updateData(Category categoryFromDB, Category category) {
        categoryFromDB.setName(category.getName());
    }

}
