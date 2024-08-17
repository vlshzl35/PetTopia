package com.sh.pettopia.Hojji.community.posts.dto;

import com.sh.pettopia.Hojji.community.posts.entity.Category;
import com.sh.pettopia.Hojji.community.posts.entity.Comment;
import com.sh.pettopia.Hojji.community.posts.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostRegistReponseDto {
    // 게시글 Id
    private Long postId;

    // 게시글 제목
    private String title;

    // 게시글 내용
    private String content;

    // 게시글 카테고리
    private Category category;

    // 게시일
    private LocalDateTime updatedAt;


    // 댓글
    private List<Comment> comments;

    // 작성자
    private String nickName;

    //    // 좋아요 수
//    private Integer likeCount;
//
//    // 조회수
//    private Integer viewCount;
//
//    // 신고 횟수
//    private Integer reportCount;


    public static PostRegistReponseDto fromPost(Post post) {
        return new PostRegistReponseDto(
                post.getPostId(),
                post.getTitle(),
                post.getContent(),
                post.getCategory(),
                post.getUpdatedAt(),
                post.getComments(),
                post.getMember().getNickName()
        );
    }
}
