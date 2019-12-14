package com.jojoldu.book.springboot.web.domain.posts;

import com.jojoldu.book.springboot.domain.posts.PostRepositoty;
import com.jojoldu.book.springboot.domain.posts.Posts;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostRepositoty postRepositoty;

    @After
    public void cleanup() {
        postRepositoty.deleteAll();
    }

    @Test
    public void selectBoard() {
        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postRepositoty.save(Posts.builder()
                           .title(title)
                           .content(content)
                           .author("ockjungbong@gmail.com")
                           .build());

        // when
        List<Posts> postsList = postRepositoty.findAll();

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}
