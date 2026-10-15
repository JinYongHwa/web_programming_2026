package kr.ac.mjc.blog01.dto;

import kr.ac.mjc.blog01.model.Article;

public class ArticleResponse {
    private boolean success;    //성공여부
    private Article article;
    private String message;     //실패시 실패 메세지

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
