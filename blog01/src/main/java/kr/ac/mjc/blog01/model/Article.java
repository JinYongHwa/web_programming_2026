package kr.ac.mjc.blog01.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no")
    private long no;        // 글번호

    @Column(name = "title",nullable = false)
    private String title;   // 글제목

    @Column(name = "body")
    private String body;    // 글내용

    @CreatedDate
    @Column(name = "writeDate")
    private LocalDateTime writeDate;    //작성시간

    public LocalDateTime getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(LocalDateTime writeDate) {
        this.writeDate = writeDate;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getNo() {
        return no;
    }

    public void setNo(long no) {
        this.no = no;
    }
}
