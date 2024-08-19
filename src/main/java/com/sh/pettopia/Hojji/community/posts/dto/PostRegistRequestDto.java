package com.sh.pettopia.Hojji.community.posts.dto;

import com.sh.pettopia.Hojji.community.posts.entity.Category;
import com.sh.pettopia.Hojji.community.posts.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PostRegistRequestDto {
    // 게시글 카테고리
    private Category category;

    // 게시글 제목
    private String title;

    // 게시글 내용
    private String content;

    public Post toPost() {
        return Post.builder()
                .category(this.category)
                .title(this.title)
                .content(this.content)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

}
