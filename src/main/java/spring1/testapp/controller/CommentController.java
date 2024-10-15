package spring1.testapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring1.testapp.dto.CommentRequest;
import spring1.testapp.dto.CommentResponse;
import spring1.testapp.service.CommentService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/articles/{articleId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponse> addComment(
            @PathVariable UUID articleId,
            @RequestBody CommentRequest commentRequest) {
        CommentResponse commentResponse = commentService.addComment(articleId, commentRequest);
        return new ResponseEntity<>(commentResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CommentResponse>> getCommentsByArticle(@PathVariable UUID articleId) {
        List<CommentResponse> comments = commentService.getCommentsByArticle(articleId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
