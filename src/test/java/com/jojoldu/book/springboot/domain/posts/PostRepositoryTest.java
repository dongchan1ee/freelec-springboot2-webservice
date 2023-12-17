package com.jojoldu.book.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@EnableJpaAuditing
@RunWith(SpringRunner.class) //JUnit에 내장된 러너를 사용하는 대신 어노테이션에 정의된 러너 클래스를 사용
@SpringBootApplication //여러 단위의 테스트를 하나의 통합된 테스트로 수행
public class PostRepositoryTest {

    @Autowired //빈 주입
    PostsRepository postsRepository;
    
    @After
    public void cleanUp() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder() //JPA문법 insert,update문법을 실행
                .content(content)
                .title(title)
                .author("dongchanlee@gmail.com")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll(); //JPA문법 테이블에 있는 모든 데이터를 들고 온다.

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2023, 12, 13, 0, 0, 0);
        postsRepository.save(Posts.builder()
                .title("title1")
                .content("content1")
                .author("author1")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>> createDate="+posts.getCreatedDate()+", modifiedDate=" + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
    
}
