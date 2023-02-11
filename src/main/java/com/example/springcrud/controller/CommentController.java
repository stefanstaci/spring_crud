package com.example.springcrud.controller;

import com.example.springcrud.dto.CommentDto;
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
    public ResponseEntity<List<CommentDto>> getComments() {
        return ResponseEntity.ok(commentService.getComments());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<List<CommentDto>> getCommentsById(@PathVariable("productId") Integer productId) {
        return ResponseEntity.ok(commentService.getCommentsById(productId));
    }

    @PostMapping("/new")
    public ResponseEntity<CommentDto> addNewComment(@RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.addNewComment(commentDto), HttpStatus.CREATED);
    }

//    @DeleteMapping("/{commentId}")
//    public ResponseEntity<List<CommentEntity>> deleteProduct(@PathVariable("commentId") Integer commentId) {
//        return new ResponseEntity<>(commentService.deleteComment(commentId), HttpStatus.ACCEPTED);
//    }
}
