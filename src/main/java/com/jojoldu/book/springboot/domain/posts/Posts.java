package com.jojoldu.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter //클래스 내의 모든 필드의 GETTER 메소드 자동생성
@NoArgsConstructor //기본생성자 자동추가
@Entity //JPA 어노테이션, 테이블과 링크될 클래스
public class Posts {

    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK생성규칙
    private Long id;

    @Column(length=500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

}
