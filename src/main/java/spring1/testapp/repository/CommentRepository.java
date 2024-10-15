package spring1.testapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring1.testapp.entity.CommentEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, UUID> {
    List<CommentEntity> findByArticleId(UUID articleId);
}
