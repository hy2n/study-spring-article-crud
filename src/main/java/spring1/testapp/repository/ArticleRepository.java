package spring1.testapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring1.testapp.entity.ArticleEntity;

import java.util.UUID;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, UUID> {

}
