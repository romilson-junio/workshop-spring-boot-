package com.romilson.workshopspringboot.resources;

import com.romilson.workshopspringboot.domain.Category;
import com.romilson.workshopspringboot.dto.CategoryDTO;
import com.romilson.workshopspringboot.services.CategoryService;
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
@RequestMapping(value="/categorias")
public class CategoryResource {

    @Autowired
    public CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll(){
        return ResponseEntity.ok().body(
                categoryService.findAll().stream().map(o -> new CategoryDTO(o)).collect(Collectors.toList()));
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<CategoryDTO>> findAllPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ){
        Page<Category> list = categoryService.findPage(page,linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(list.map(o -> new CategoryDTO(o)));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> find(@PathVariable("id") Integer id){
        return ResponseEntity.ok().body(categoryService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody CategoryDTO dto){
        Category category = categoryService.insert(categoryService.fromDTO(dto));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Category> update(@PathVariable("id") Integer id, @Valid @RequestBody CategoryDTO dto){
        Category category = categoryService.update(id, categoryService.fromDTO(dto));
        return ResponseEntity.ok().body(category);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
