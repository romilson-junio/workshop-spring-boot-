package com.romilson.workshopspringboot.services;

import com.romilson.workshopspringboot.domain.Category;
import com.romilson.workshopspringboot.domain.Product;
import com.romilson.workshopspringboot.dto.ProductDTO;
import com.romilson.workshopspringboot.repositories.CategoryRepository;
import com.romilson.workshopspringboot.repositories.ProductRepository;
import com.romilson.workshopspringboot.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Product findById(Integer id){
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(() ->
                new ObjectNotFoundException("Product not found for id: ".concat(String.valueOf(id)).concat(", Tipo: ").concat(Product.class.getName())));
    }

    public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Category> categorias = categoryRepository.findAllById(ids);
        return productRepository.findDistinctByNameContainingAndCategoriesIn(name, categorias, pageRequest);

    }

}
