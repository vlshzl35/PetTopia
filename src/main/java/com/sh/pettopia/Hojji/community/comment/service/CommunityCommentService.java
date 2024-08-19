package com.sh.pettopia.Hojji.community.comment.service;

import com.sh.pettopia.Hojji.community.comment.dto.CommentRegistRequestDto;

import com.sh.pettopia.Hojji.community.comment.dto.CommunityCommentResponseDto;
import com.sh.pettopia.Hojji.community.comment.entity.CommunityComment;
import com.sh.pettopia.Hojji.community.comment.repository.CommunityCommentRepository;
import com.sh.pettopia.Hojji.user.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CommunityCommentService {
    private final CommunityCommentRepository commentRepository;

    // 댓글 등록
    public void registComment(Member member, CommentRegistRequestDto commentRegistDto) {
        // 1. commentRegistDto를 CommunityComment 테이블에 저장하기 위해 Comment Entity로 변환합니다.
        CommunityComment comment = commentRegistDto.toCommunityComment();
        log.debug("Comment 내용 = {}", comment.getCommentContent());

        // 2. Comment Writer를 설정합니다.
        comment.setWriter(member);
        log.debug("Comment 작성자 = {}", comment.getMember().getNickName());

        // 3. Comment를 등록합니다.
        commentRepository.save(comment);
    }

    // 게시글을 조회한 후, 해당 게시글에 대한 comment를 가져오는 메소드입니다.
    public List<CommunityCommentResponseDto> findByPostId(Long postId) {
        // 1. 게시글 Id에 해당하는 모든 comment를 List로 반환받습니다.
         List<CommunityComment> comments =  commentRepository.findByPost_PostId(postId);

        // 2. Lsit의 모든 Comment 엔티티를 CommentResponseDto로 변환합니다.
        List<CommunityCommentResponseDto> commentDtos = comments.stream()
                .map(CommunityCommentResponseDto::fromCommunityComment)
                .collect(Collectors.toList());
        return commentDtos;

    }
}
