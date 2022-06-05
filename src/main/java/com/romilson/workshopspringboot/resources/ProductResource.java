package com.romilson.workshopspringboot.resources;

import com.romilson.workshopspringboot.domain.Category;
import com.romilson.workshopspringboot.domain.Ordered;
import com.romilson.workshopspringboot.domain.Product;
import com.romilson.workshopspringboot.dto.CategoryDTO;
import com.romilson.workshopspringboot.dto.ProductDTO;
import com.romilson.workshopspringboot.resources.utils.URL;
import com.romilson.workshopspringboot.services.OrderedService;
import com.romilson.workshopspringboot.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/produtos")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/page")
    public ResponseEntity<Page<ProductDTO>> findAllPage(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "categories", defaultValue = "") String categories,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ){
        Page<Product> list = productService.search(URL.decodeParam(name), URL.decodeIntegerList(categories), page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(list.map(o -> new ProductDTO(o)));
    }
	
}
