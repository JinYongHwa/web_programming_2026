package kr.ac.mjc.blog01.service;

import jakarta.transaction.Transactional;
import kr.ac.mjc.blog01.dto.ArticleDto;
import kr.ac.mjc.blog01.model.Article;
import kr.ac.mjc.blog01.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    public Article getItem(long no){
        Optional<Article> optional=articleRepository.findById(no);
        if(optional.isEmpty()){
            return null;
        }
        return optional.get();
    }

    public List<Article> getList(){
        return articleRepository.findAll();
    }
    //글이 있는지 확인하고 있으면 글 삭제
    public boolean delete(long no){
        Article article=getItem(no);
        if(article==null){  //article 이 null 이면 글이 없음
            return false;
        }
        articleRepository.deleteById(no);   //글이 있는거 확인됐으니 삭제
        return true;
    }

    //글 수정하기
    @Transactional
    public boolean modify(long no, ArticleDto articleDto){
        Article article=getItem(no);    //데이터베이스에서 글 가져오기
        article.setTitle(articleDto.getTitle());
        article.setBody(articleDto.getBody());
        return true;
    }
}
