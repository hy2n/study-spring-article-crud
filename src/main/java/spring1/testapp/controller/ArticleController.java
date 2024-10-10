package spring1.testapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring1.testapp.dto.ArticleRequest;
import spring1.testapp.dto.ArticleResponse;
import spring1.testapp.service.ArticleService;

import java.util.List;

@RestController() //restcontroller 선언
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/")
    public List<ArticleResponse> getAllArticle(){
        return articleService.GetAllArticle();
    }

    @PostMapping("/")
    public ArticleResponse createArticle(@RequestBody ArticleRequest articleRequest){
        return articleService.CreateArticle(articleRequest);
    }

    @PutMapping("/{articleId}")
    public ArticleResponse updateArticle(@PathVariable String articleId, @RequestBody ArticleRequest articleRequest){
        return articleService.UpdateArticle(articleId,articleRequest);
    }

    @DeleteMapping("/{articleId}")
    public void deleteArticle(@PathVariable String articleId){
        articleService.DeleteArticle(articleId);
    }
}
