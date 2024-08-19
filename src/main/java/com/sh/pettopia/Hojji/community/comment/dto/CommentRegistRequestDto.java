package com.sh.pettopia.Hojji.community.comment.dto;

import com.sh.pettopia.Hojji.community.comment.entity.CommunityComment;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentRegistRequestDto {
    private String content;

    public CommunityComment toCommunityComment() {
        return CommunityComment.builder()
                .commentContent(this.content)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
