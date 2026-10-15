package kr.ac.mjc.blog01.repository;

import kr.ac.mjc.blog01.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article,Long>{

    Article save(Article article);

    Optional<Article> findById(long no);

    List<Article> findAll();        //글 전체 가져오기
}
