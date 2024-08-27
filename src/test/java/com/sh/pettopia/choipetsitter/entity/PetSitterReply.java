package com.sh.pettopia.choipetsitter.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PetSitterReply {
    // 회원리뷰에 대한 답글
    // 필요한 속성, 아딴 게시글에. 회원의 아이디, 펫시터의 아이디, 펫시터가 등록한 댓글
    @Column(name = "reply_text")
    private String replyText; // 답글 내용

    @Column(name = "reply_created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "reply_updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public void changeReplyText(String replyText) {
        this.replyText=replyText;
    }
    public void registerReplyText(String replyText) {
        this.replyText=replyText;
    }





}
