package com.example.springcrud.controller;

import com.example.springcrud.dto.ProductDto;
import com.example.springcrud.entity.ProductEntity;
import com.example.springcrud.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("productId") Integer productId) {
        return new ResponseEntity<>(productService.getProductById(productId), HttpStatus.CREATED);
    }

    @PostMapping("/new")
    public ResponseEntity<ProductDto> addNewProduct(@RequestBody ProductDto product) {

        return new ResponseEntity<ProductDto>(productService.addNewProduct(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<List<ProductEntity>> deleteProduct(@PathVariable("productId") Integer productId) {
        return new ResponseEntity<>(productService.deleteProduct(productId), HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductEntity> updateProduct(@PathVariable("productId") Integer productId,
                              @RequestParam(name = "title", required = false) String title,
                              @RequestParam(name = "description",required = false) String description,
                              @RequestParam(name = "price",required = false) Double price){
        return new ResponseEntity<ProductEntity>(productService.updateProduct(productId, title, description, price), HttpStatus.CREATED);
    }


}
