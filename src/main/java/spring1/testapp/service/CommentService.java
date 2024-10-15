package spring1.testapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring1.testapp.dto.CommentRequest;
import spring1.testapp.dto.CommentResponse;
import spring1.testapp.entity.ArticleEntity;
import spring1.testapp.entity.CommentEntity;
import spring1.testapp.repository.ArticleRepository;
import spring1.testapp.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    @Transactional
    public CommentResponse addComment(UUID articleId, CommentRequest commentRequest) {
        Optional<ArticleEntity> articleOptional = articleRepository.findById(articleId);
        if (articleOptional.isPresent()) {
            ArticleEntity article = articleOptional.get();
            CommentEntity comment = new CommentEntity();
            comment.setContent(commentRequest.getContent());
            comment.setAuthor(commentRequest.getAuthor());
            comment.setArticle(article);

            CommentEntity savedComment = commentRepository.save(comment);

            return new CommentResponse(
                    savedComment.getId().toString(),
                    savedComment.getContent(),
                    savedComment.getAuthor()
            );
        } else {
            throw new RuntimeException("게시글을 찾을 수 없습니다.");
        }
    }

    @Transactional(readOnly = true)
    public List<CommentResponse> getCommentsByArticle(UUID articleId) {
        List<CommentEntity> comments = commentRepository.findByArticleId(articleId);
        List<CommentResponse> commentResponses = new ArrayList<>();

        for (CommentEntity comment : comments) {
            CommentResponse response = new CommentResponse(
                    comment.getId().toString(),
                    comment.getContent(),
                    comment.getAuthor()
            );
            commentResponses.add(response);
        }
        return commentResponses;
    }
}
