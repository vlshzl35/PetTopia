package com.sh.pettopia.Hojji.community.posts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Embeddable
@Data
@AllArgsConstructor
public class Comment {
    // 내용
    @Column(name = "comment_content",nullable = false)
    private String commentContent;

    // 등록 일자
    @Column(name = "comment_created_at")
    private LocalDateTime createdAt;

    // 수정 일자
    @Column(name = "comment_updated_at")
    private LocalDateTime updatedAt;

    // 기본 생성자로 기본 값을 설정합니다.
    public Comment() {
        this.commentContent = "";
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
