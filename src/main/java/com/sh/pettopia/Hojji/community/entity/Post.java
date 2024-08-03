package com.sh.pettopia.Hojji.community.entity;

import com.sh.pettopia.Hojji.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "tbl_community")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long communityId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @Column(name = "like_count")
    private int likeCount;

    @Column(name = "report_count")
    private int reportCount;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

}
