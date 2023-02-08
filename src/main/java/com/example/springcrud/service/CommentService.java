package com.example.springcrud.service;

import com.example.springcrud.dto.CommentDto;
import com.example.springcrud.entity.CommentEntity;
import com.example.springcrud.entity.ProductEntity;
import com.example.springcrud.repository.CommentRepository;
import com.example.springcrud.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;

    public List<CommentEntity> getComments() {
        return commentRepository.findAll();
    }

    public CommentEntity addNewComment(CommentDto commentDto) {
        CommentEntity comment = new CommentEntity();
        comment.setContent(commentDto.getContent());
        ProductEntity product = productRepository.getReferenceById(commentDto.getProductId());
        comment.setProduct(product);
        return commentRepository.save(comment);
    }

    public List<CommentEntity> deleteComment(Integer commentID) {
        boolean exists = commentRepository.existsById(commentID);
        if (!exists) {
            throw new IllegalStateException("comment with id " + commentID + " does not exist");
        }
        commentRepository.deleteById(commentID);
        return commentRepository.findAll();
    }
}
