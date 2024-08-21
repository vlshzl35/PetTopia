package com.sh.pettopia.Hojji.community.comment.entity;

import com.sh.pettopia.Hojji.community.comment.dto.CommuCommentResponseDto;
import com.sh.pettopia.Hojji.community.posts.entity.Post;
import com.sh.pettopia.Hojji.user.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "tbl_community_comment")
@Getter
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CommunityComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    // 내용
    @Column(name = "comment_content", nullable = false)
    private String commentContent;

    // 등록 일자
    @Column(name = "comment_created_at")
    private LocalDateTime createdAt;

//    // 수정 일자
//    @Column(name = "comment_updated_at")
//    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member; // 작성자

//    // 부모 댓글
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "parent_id")
//    private CommunityComment parent;
//
//    // 자식 댓글
//    @OneToMany(mappedBy = "parent", orphanRemoval = true)
//    private List<CommunityComment> children = new ArrayList<>();


    // 댓글 작성자를 셋팅하는 메소드입니다.
    public void setWriter(Member member) {
        this.member = member;
    }

}
