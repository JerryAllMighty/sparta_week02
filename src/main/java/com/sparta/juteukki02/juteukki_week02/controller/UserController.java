package com.sparta.juteukki02.juteukki_week02.controller;

import com.sparta.juteukki02.juteukki_week02.model.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;
    private final UserRepository commentRepository;

    @GetMapping("/api/comments/{postid}")
    public List<Comment> getPosts(@PathVariable Long postid){
        return commentRepository.findByPostId(postid.toString());
    }
//    @GetMapping("/api/comments")
//    public List<CommentsContentsOnly> getPosts(){
//        return commentRepository.findContents();
//    }


    @PostMapping("/api/comments")
    public Comment createProduct(@RequestBody CommentDto commentDto) {
        Comment comment = new Comment(commentDto);
        commentRepository.save(comment);
        return comment;
    }
    @PutMapping("/api/comments/{id}")
    public Long updateProduct(@PathVariable Long id, @RequestBody CommentDto commentDto) {
        return commentservice.update(id, commentDto);
    }

    @DeleteMapping("/api/comments/{id}")
    public Long deleteMemo(@PathVariable Long id) {
        commentRepository.deleteById(id);
        return id;
    }

}
