package kr.ac.mjc.blog01.controller;

import jakarta.servlet.http.HttpSession;
import kr.ac.mjc.blog01.model.Article;
import kr.ac.mjc.blog01.model.User;
import kr.ac.mjc.blog01.service.ArticleService;
import kr.ac.mjc.blog01.service.UserService;
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

    @Autowired
    UserService userService;

    @GetMapping("/")
    public ModelAndView main(HttpSession session){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("main");    // main.html 템플릿으로 그려라

        List<Article> articleList=articleService.getList();
        mav.addObject("list",articleList);

        //로그인이 되어있을경우 로그인된 사용자 정보도 view 로 보내기
        if(session.getAttribute("userId")!=null){
           String email= (String)session.getAttribute("userId");
           User user=userService.getUserInfo(email);
           mav.addObject("user",user);  //로그인된 사용자정보 view 로보내기
        }

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

    @GetMapping("/modify/{no}")
    public ModelAndView modify(@PathVariable("no") long no){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("modify");
        Article article=articleService.getItem(no);
        mav.addObject("article",article);
        return mav;
    }

    @GetMapping("/join")
    public String join(){
        return "join";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("userId");
        return "redirect:/";
    }

}
