package com.sh.pettopia.Hojji.community.comment.entity;

import com.sh.pettopia.Hojji.community.posts.entity.Post;
import com.sh.pettopia.Hojji.user.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLJoinTableRestriction;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_comment")
@Getter
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    // 내용
    @Column(name = "comment_content", nullable = false)
    private String commentContent;

    // 등록 일자
    @Column(name = "comment_created_at")
    private LocalDateTime createdAt;

    // 수정 일자
    @Column(name = "comment_updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member; // 작성자

//    // 기본 생성자로 기본 값을 설정합니다.
//    public Comment() {
//        this.commentContent = "";
//        this.createdAt = LocalDateTime.now();
//        this.updatedAt = LocalDateTime.now();
//    }
}
