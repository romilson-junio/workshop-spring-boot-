package com.romilson.workshopspringboot.resources;

import com.romilson.workshopspringboot.domain.Category;
import com.romilson.workshopspringboot.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/categorias")
public class CategoryResource {

    @Autowired
    public CategoryService categoryService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCategoria(@PathVariable("id") Integer id){
        return ResponseEntity.ok().body(categoryService.findById(id));
    }
	
}
