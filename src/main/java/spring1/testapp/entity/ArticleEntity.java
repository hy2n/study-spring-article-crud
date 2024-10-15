package spring1.testapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.mapping.Array;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "articles")
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "title")
    private String Title;

    @Column(name = "content")
    private String Content;

    @Column(name = "author")
    private String author;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentEntity> comments;
}
