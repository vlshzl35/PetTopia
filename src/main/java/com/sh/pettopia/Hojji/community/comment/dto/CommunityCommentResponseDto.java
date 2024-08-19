package com.sh.pettopia.Hojji.community.comment.dto;

import com.sh.pettopia.Hojji.community.comment.entity.CommunityComment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommunityCommentResponseDto {
    private String nickName;
    private String content;
    private LocalDateTime createdAt;

    public static CommunityCommentResponseDto fromCommunityComment(CommunityComment communityComment) {
        return new CommunityCommentResponseDto(
                communityComment.getMember().getNickName(),
                communityComment.getCommentContent(),
                communityComment.getCreatedAt()
        );
    }
}
