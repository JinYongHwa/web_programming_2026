package kr.ac.mjc.blog01.controller;

import jakarta.websocket.server.PathParam;
import kr.ac.mjc.blog01.dto.ArticleDto;
import kr.ac.mjc.blog01.dto.ArticleResponse;
import kr.ac.mjc.blog01.model.Article;
import kr.ac.mjc.blog01.repository.ArticleRepository;
import kr.ac.mjc.blog01.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArticleController {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    ArticleService articleService;

    @PostMapping("/api/article")
    public ResponseEntity<ArticleResponse> write(@RequestBody ArticleDto articleDto){
        Article article=new Article();
        String title=articleDto.getTitle();
        ArticleResponse response=new ArticleResponse();
        if(title==null||title.equals("")){  //제목이 null 이거나 제목이 빈값이면
            response.setSuccess(false); //실패한 응답
            response.setMessage("제목을 입력해 주세요");
            return ResponseEntity.ok(response);
        }
        String body=articleDto.getBody();
        if(body.indexOf("시발")>-1){  //글 내용에 비속어가 포함되어있는경우
            response.setSuccess(false);
            response.setMessage("글 내용에 비속어가 있으면 안됩니다");
            return  ResponseEntity.ok(response);
        }

        article.setTitle(articleDto.getTitle());
        article.setBody(articleDto.getBody());
        Article savedArticle=articleRepository.save(article);

        response.setSuccess(true);
        response.setArticle(savedArticle);  //글번호가 포함된 데이터베이스에 저장된 article
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/api/article/{no}")
    public ResponseEntity<ArticleResponse> delete(@PathVariable("no") long no){
        //서비스에 있는 삭제로직 적용후 삭제 여부 반환받기
        boolean result=articleService.delete(no);
        ArticleResponse response=new ArticleResponse();
        response.setSuccess(result);
        if(result==false){
            response.setMessage("존재하지 않는 글입니다");
        }
        return ResponseEntity.ok(response);
    }
    @PostMapping("/api/article/modify/{no}")
    public ResponseEntity<ArticleResponse> modify(@PathVariable("no") long no,@RequestBody ArticleDto articleDto){
        ArticleResponse response=new ArticleResponse();
        boolean result=articleService.modify(no,articleDto);
        response.setSuccess(result);
        return ResponseEntity.ok(response);
    }


}
