package com.sh.pettopia.Hojji.community.comment.dto;

import com.sh.pettopia.Hojji.community.comment.entity.CommunityComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommuCommentResponseDto {
    private Long id;
    private String nickName;
    private String content;
    private LocalDateTime createdAt;
    private Long memberId;

    public static CommuCommentResponseDto fromCommunityComment(CommunityComment communityComment) {
        return CommuCommentResponseDto.builder()
                .id(communityComment.getCommentId())
                .nickName(communityComment.getMember().getNickName())
                .content(communityComment.getCommentContent())
                .createdAt(communityComment.getCreatedAt())
                .memberId(communityComment.getMember().getId())
                .build();
    }
}
