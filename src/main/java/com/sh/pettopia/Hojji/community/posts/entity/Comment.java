package com.sh.pettopia.Hojji.community.posts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    // 내용
    @Column(name = "comment_content",nullable = false)
    private String commentContent;

    // 등록 일자
    @Column(name = "comment_created_at")
    private Timestamp createdAt;

    // 수정 일자
    @Column(name = "comment_updated_at")
    private Timestamp updatedAt;
}
