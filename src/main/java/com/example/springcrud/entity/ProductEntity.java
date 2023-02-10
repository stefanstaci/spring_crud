package com.example.springcrud.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
@Getter
@Setter
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    private Double price;
    @OneToMany(mappedBy = "product")
    private List<CommentEntity> comment = new ArrayList<>();
}
