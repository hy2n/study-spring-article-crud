package spring1.testapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring1.testapp.dto.ArticleRequest;
import spring1.testapp.dto.ArticleResponse;
import spring1.testapp.entity.ArticleEntity;
import spring1.testapp.repository.ArticleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Transactional
    public List<ArticleResponse> GetAllArticle() {
        List<ArticleEntity> articles = articleRepository.findAll();
        List<ArticleResponse> articleResponses = new ArrayList<>();

        for (ArticleEntity article : articles) {
            ArticleResponse response = new ArticleResponse(
                    article.getId().toString(),
                    article.getTitle(),
                    article.getContent(),
                    article.getAuthor()
            );
            articleResponses.add(response);
        }
        return articleResponses;
    }

    @Transactional
    public ArticleResponse CreateArticle(ArticleRequest articleRequest) {
        ArticleEntity articleEntity = new ArticleEntity(); //새로운 데이터 객체 찍음

        articleEntity.setTitle(articleRequest.getTitle()); //데이터 객체에 dto 데이터 넣기
        articleEntity.setContent(articleRequest.getContent());
        articleEntity.setAuthor(articleRequest.getAuthor());
        
        ArticleEntity savedArticle = articleRepository.save(articleEntity); //새로 만든 데이터 객체 repository 에 추가하기

        return new ArticleResponse( //반환값을 dto에 맞춰서 만들어서 보내기
                savedArticle.getId().toString(),
                savedArticle.getTitle(),
                savedArticle.getContent(),
                savedArticle.getAuthor()
        );
    }

    @Transactional
    public ArticleResponse UpdateArticle(String articleId, ArticleRequest articleRequest) {
        Optional<ArticleEntity> optionalArticle = articleRepository.findById(UUID.fromString(articleId)); //ID로 개시글 조회
        
        if (optionalArticle.isPresent()) { // 만약 게시글이 등록되었다면
            ArticleEntity articleEntity = optionalArticle.get();
            
            articleEntity.setTitle(articleRequest.getTitle());
            articleEntity.setContent(articleRequest.getContent());
            articleEntity.setAuthor(articleRequest.getAuthor());

            ArticleEntity updatedArticle = articleRepository.save(articleEntity);  //수정한 데이터 객체 repository 에 추가하기

            return new ArticleResponse( //반환값을 dto에 맞춰서 만들어서 보내기
                    updatedArticle.getId().toString(),
                    updatedArticle.getTitle(),
                    updatedArticle.getContent(),
                    updatedArticle.getAuthor()
            );
        } else {
            throw new RuntimeException("게시글을 찾을 수 없습니다."); // 예외 처리
        }
    }

    @Transactional
    public void DeleteArticle(String articleId) {
        Optional<ArticleEntity> optionalArticle = articleRepository.findById(UUID.fromString(articleId)); // ID로 게시글 조회
        if (optionalArticle.isPresent()) { //게시글 있으면
            articleRepository.delete(optionalArticle.get()); // 게시글 삭제
        } else {
            throw new RuntimeException("게시글을 찾을 수 없습니다."); // 예외 처리
        }
    }
}
