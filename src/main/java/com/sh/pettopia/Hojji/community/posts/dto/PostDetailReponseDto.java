package com.sh.pettopia.Hojji.community.posts.dto;

import com.sh.pettopia.Hojji.community.posts.entity.Category;
import com.sh.pettopia.Hojji.community.posts.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDetailReponseDto {
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

    // 작성자
    private String nickName;

    // 작성자 Id
    private Long memberId;

    //    // 좋아요 수
//    private Integer likeCount;
//
//    // 조회수
//    private Integer viewCount;
//
//    // 신고 횟수
//    private Integer reportCount;


    public static PostDetailReponseDto fromPost(Post post) {
        return new PostDetailReponseDto(
                post.getPostId(),
                post.getTitle(),
                post.getContent(),
                post.getCategory(),
                post.getUpdatedAt(),
                post.getMember().getNickName(),
                post.getMember().getId()
        );
    }
}
