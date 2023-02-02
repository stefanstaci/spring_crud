package com.example.springcrud.repository;

import com.example.springcrud.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    Optional<ProductEntity> findProductByTitle(String title);
}
