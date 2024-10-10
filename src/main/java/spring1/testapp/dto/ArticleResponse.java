package spring1.testapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponse {

    private String article_id;
    private String title;
    private String content;
    private String author;
}
