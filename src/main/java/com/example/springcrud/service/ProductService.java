package com.example.springcrud.service;

import com.example.springcrud.dto.ProductDto;
import com.example.springcrud.entity.ProductEntity;
import com.example.springcrud.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public List<ProductDto> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductDto(
                        product.getId(),
                        product.getTitle(),
                        product.getDescription(),
                        product.getPrice()
                ))
                .collect(Collectors.toList());
    }

//    public ProductEntity getProductById(Integer productId) {
//        boolean exists = productRepository.existsById(productId);
//        if (!exists) {
//            throw new IllegalStateException("product with id " + productId + " does not exist");
//        }
//        return productRepository.findById(productId).orElseThrow();
//    }

    public ProductDto getProductById(Integer productId) {
        if (!productRepository.existsById(productId)) {
            throw new IllegalStateException("product with id " + productId + " does not exist");
        }
        return productRepository.findById(productId)
                .map(product -> new ProductDto(
                        product.getId(),
                        product.getTitle(),
                        product.getDescription(),
                        product.getPrice()
                )).orElseThrow();
    }

    public ProductDto addNewProduct(ProductDto productDto) {
        ProductEntity product = new ProductEntity();
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        Optional<ProductEntity> productOptional = productRepository.findProductByTitle(product.getTitle());
        if (productOptional.isPresent()){
            throw new IllegalStateException("product already exist");
        }
        productRepository.save(product);
        return new ProductDto(
                product.getId(),
                product.getTitle(),
                productDto.getDescription(),
                product.getPrice()
                );

    }

    public List<ProductEntity> deleteProduct(Integer productId) {
        boolean exists = productRepository.existsById(productId);
        if (!exists) {
            throw new IllegalStateException("product with id " + productId + " does not exist");
        }
        productRepository.deleteById(productId);
        return productRepository.findAll();
    }

    @Transactional
    public ProductEntity updateProduct(Integer productId, String title, String description, Double price) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalStateException("product with id " + productId + " does not exist"));

        if (title != null && title.length() > 0 && !Objects.equals(product.getTitle(), title)) {
            product.setTitle(title);
        }

        if (description != null && description.length() > 0 && !Objects.equals(product.getDescription(), description)) {
            product.setDescription(description);
        }
        if (price != null && !Objects.equals(product.getPrice(), price)) {
            product.setPrice(price);
        }
        return productRepository.save(product);
    }
}
