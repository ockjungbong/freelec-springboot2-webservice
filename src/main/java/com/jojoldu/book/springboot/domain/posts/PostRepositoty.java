package com.jojoldu.book.springboot.domain.posts;


import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepositoty extends JpaRepository<Posts, Long> {
}
