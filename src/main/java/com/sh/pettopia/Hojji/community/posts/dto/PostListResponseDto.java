package com.sh.pettopia.Hojji.community.posts.dto;

import com.sh.pettopia.Hojji.community.posts.entity.Category;
import com.sh.pettopia.Hojji.community.posts.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostListResponseDto {
    // 게시글 Id
    private Long postId;

    // 카테고리
    private Category category;

    // 제목
    private String title;

    // 작성자
    private String writer;

    // 작성일
    private LocalDateTime updatedAt;

    // 조회수
    private Integer views;

    public static PostListResponseDto fromPost(Post post) {
        return new PostListResponseDto(
                post.getPostId(),
                post.getCategory(),
                post.getTitle(),
                post.getMember().getNickName(),
                post.getUpdatedAt(),
                post.getViewCount()
        );
    }
}