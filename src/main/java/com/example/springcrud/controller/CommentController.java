package com.example.springcrud.controller;

import com.example.springcrud.dto.CommentDto;
import com.example.springcrud.entity.CommentEntity;
import com.example.springcrud.entity.ProductEntity;
import com.example.springcrud.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/all")
    public ResponseEntity<List<CommentEntity>> getComments() {
        return ResponseEntity.ok(commentService.getComments());
    }

    @PostMapping("/new")
    public ResponseEntity<CommentEntity> addNewComment(@RequestBody CommentDto commentDto) {
        return new ResponseEntity<CommentEntity>(commentService.addNewComment(commentDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<List<CommentEntity>> deleteProduct(@PathVariable("commentId") Integer commentId) {
        return new ResponseEntity<>(commentService.deleteComment(commentId), HttpStatus.CREATED);
    }
}
