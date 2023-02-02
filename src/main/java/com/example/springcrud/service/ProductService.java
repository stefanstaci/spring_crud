package com.example.springcrud.service;

import com.example.springcrud.entity.ProductEntity;
import com.example.springcrud.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<ProductEntity> getProducts() {
        return productRepository.findAll();
    }

    public void addNewProduct(ProductEntity product) {
        Optional<ProductEntity> productOptional = productRepository.findProductByTitle(product.getTitle());
        if (productOptional.isPresent()){
            throw new IllegalStateException("product already exist");
        }
        productRepository.save(product);
    }

    public void deleteProduct(Integer productId) {
        boolean exists = productRepository.existsById(productId);
        if (!exists) {
            throw new IllegalStateException("product with id " + productId + " does not exist");
        }
        productRepository.deleteById(productId);
    }

    @Transactional
    public void updateProduct(Integer productId, String title, String description, Double price) {
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
    }
}
