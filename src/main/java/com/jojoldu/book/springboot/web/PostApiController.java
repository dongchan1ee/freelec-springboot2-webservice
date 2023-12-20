package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import com.jojoldu.book.springboot.service.posts.PostsService;
import com.jojoldu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    //RequestBody 라이언트와 서버 간의 HTTP 통신에서 요청과 응답을 보낼 때, 필요한 데이터를 담아서 보내는 공간이 바로 body
    //이때 요청하는 요청 본문을 requestBody, 응답하는 응답 본문을 responseBody
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    //@PathVariable HTTP 요청의 URL 경로에서 특정 변수 값을 추출하는 데 사용 {id}
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long Delete(@PathVariable Long id) {
        postsService.delete(id);

        return id;
    }
}
