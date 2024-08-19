package com.sh.pettopia.Hojji.community.posts.repository;

import com.sh.pettopia.Hojji.community.posts.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByContent(String q, Pageable pageable);

    Post findByPostId(Long postId);
}
