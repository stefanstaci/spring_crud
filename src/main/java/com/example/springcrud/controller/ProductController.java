package com.example.springcrud.controller;

import com.example.springcrud.entity.ProductEntity;
import com.example.springcrud.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/all")
    public List<ProductEntity> getProducts() {
        return productService.getProducts();
    }

    @PostMapping("/new")
    public void addNewProduct(@RequestBody ProductEntity product) {
        productService.addNewProduct(product);
    }

    @DeleteMapping(path = "{productId}")
    public void deleteProduct(@PathVariable("productId") Integer productId) {
        productService.deleteProduct(productId);
    }

    @PutMapping(path = ("{productId}"))
    public void updateProduct(@PathVariable("productId") Integer productId,
                              @RequestParam(name = "title", required = false) String title,
                              @RequestParam(name = "description",required = false) String description,
                              @RequestParam(name = "price",required = false) Double price){
        productService.updateProduct(productId, title, description, price);
    }


}
