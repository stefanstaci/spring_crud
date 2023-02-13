package com.example.springcrud.service;

import com.example.springcrud.dto.CommentDto;
import com.example.springcrud.entity.CommentEntity;
import com.example.springcrud.entity.ProductEntity;
import com.example.springcrud.repository.CommentRepository;
import com.example.springcrud.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;

    public List<CommentDto> getComments() {
        return commentRepository.findAll()
                .stream()
                .map(comment -> new CommentDto(
                        comment.getContent(),
                        comment.getProduct().getId()
                ))
                .collect(Collectors.toList());
    }

    public List<CommentDto> getCommentsById(Integer productId) {
        if (productRepository.findById(productId).isEmpty()){
            throw new IllegalStateException("product with id " + productId + " does not exist");
        }
        return commentRepository.findAllByProductId(productId)
                .stream()
                .map(comment -> new CommentDto(
                        comment.getContent(),
                        comment.getProduct().getId()
                ))
                .collect(Collectors.toList());
    }

    public CommentDto addNewComment(CommentDto commentDto) {
        CommentEntity comment = new CommentEntity();
        comment.setContent(commentDto.getContent());
        ProductEntity product = productRepository.getReferenceById(commentDto.getProductId());
        comment.setProduct(product);
        commentRepository.save(comment);
        return new CommentDto(comment.getContent(), comment.getProduct().getId());
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
