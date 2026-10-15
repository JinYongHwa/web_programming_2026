package kr.ac.mjc.blog01.service;

import kr.ac.mjc.blog01.model.Article;
import kr.ac.mjc.blog01.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    public Article getItem(int no){
        return articleRepository.findById(no).get();
    }

    public List<Article> getList(){
        return articleRepository.findAll();
    }
}
