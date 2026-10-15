package kr.ac.mjc.blog01.controller;

import kr.ac.mjc.blog01.model.Article;
import kr.ac.mjc.blog01.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ViewController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/")
    public ModelAndView main(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("main");    // main.html 템플릿으로 그려라

        List<Article> articleList=articleService.getList();
        mav.addObject("list",articleList);

        return mav;
    }

    @GetMapping("/write")
    public String write(){
        return "write";
    }

    @GetMapping("/view/{no}")
    public ModelAndView view(@PathVariable(name="no") int no){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("view");
        System.out.println("no"+no);
        Article article=articleService.getItem(no);
        System.out.println(article.getTitle());
        mav.addObject("article",article);
        return mav;
    }

}
