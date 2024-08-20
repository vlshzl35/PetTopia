package com.sh.pettopia.Hojji.community.comment.dto;

import com.sh.pettopia.Hojji.community.comment.entity.CommunityComment;
import com.sh.pettopia.Hojji.community.posts.entity.Post;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommuCommentRegistRequestDto {
    private Long postId;
    private String content;

    public CommunityComment toCommunityComment(Post post) {
        return CommunityComment.builder()
                .post(post)
                .commentContent(content)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
