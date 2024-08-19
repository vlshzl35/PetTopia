package com.sh.pettopia.Hojji.community.posts.entity;

import com.sh.pettopia.Hojji.community.comment.entity.Comment;
import com.sh.pettopia.Hojji.community.posts.dto.PostUpdateRequestDto;
import com.sh.pettopia.Hojji.user.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "tbl_community")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Post {
    private static final Logger log = LoggerFactory.getLogger(Post.class);
    // AUTO_INCREMENT로 관리되는 postId
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    // 게시글 제목
    @Column(nullable = false)
    private String title;

    // 게시글 내용
    @Column(nullable = false)
    private String content;

    // 게시글 카테고리
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    // 게시일
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // 게시글 수정일
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // 좋아요 수
    @Column(name = "like_count")
    private Integer likeCount;

    // 조회 수
    @Column(name = "view_count")
    private Integer viewCount;

    // 신고 횟수
    @Column(name = "report_count")
    private Integer reportCount;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;


    // 게시글 작성자를 셋팅하는 메소드입니다.
    public void setWriter(Member member) {
        this.member = member;
    }

    // 게시글을 수정하는 메소드입니다.
    public void update(PostUpdateRequestDto dto) {
        this.category = dto.getCategory();
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }
}
