package com.sh.pettopia.Hojji.community.comment.repository;

import com.sh.pettopia.Hojji.community.comment.entity.CommunityComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunityCommentRepository extends JpaRepository<CommunityComment, Long> {
    // CommunityComment의 post 필드에 연결된 Post 엔티티의 postId를 기준으로 검색합니다.
    List<CommunityComment> findByPost_PostId(Long postId);
}
