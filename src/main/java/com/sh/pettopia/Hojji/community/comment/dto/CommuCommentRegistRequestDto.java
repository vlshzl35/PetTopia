package com.sh.pettopia.Hojji.community.comment.dto;

import com.sh.pettopia.Hojji.community.comment.entity.CommunityComment;
import com.sh.pettopia.Hojji.community.posts.entity.Post;
import com.sh.pettopia.Hojji.user.member.entity.Member;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommuCommentRegistRequestDto {
//    private Long postId;
    private String content;

    public CommunityComment toCommunityComment(Post post, Member member) {
        return CommunityComment.builder()
                .member(member)
                .post(post)
                .commentContent(this.content)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
