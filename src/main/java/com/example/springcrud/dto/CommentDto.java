package com.example.springcrud.dto;

import com.example.springcrud.entity.ProductEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private String content;
    private Integer productId;
}
