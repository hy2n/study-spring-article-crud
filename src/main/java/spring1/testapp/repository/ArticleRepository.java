package spring1.testapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring1.testapp.entity.ArticleEntity;

import java.util.UUID;

public interface ArticleRepository extends JpaRepository<ArticleEntity, UUID> {

}
