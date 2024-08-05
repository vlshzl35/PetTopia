package com.sh.pettopia.Hojji.community.entity;

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
    private String content;
    // 등록 일자
    private LocalDateTime createdAt;
    // 수정 일자
    private Timestamp updatedAt;
}
