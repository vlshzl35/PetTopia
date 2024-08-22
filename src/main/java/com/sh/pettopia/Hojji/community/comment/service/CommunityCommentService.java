package com.sh.pettopia.Hojji.community.comment.service;

import com.sh.pettopia.Hojji.community.comment.dto.CommuCommentRegistRequestDto;

import com.sh.pettopia.Hojji.community.comment.dto.CommuCommentResponseDto;
import com.sh.pettopia.Hojji.community.comment.entity.CommunityComment;
import com.sh.pettopia.Hojji.community.comment.repository.CommunityCommentRepository;
import com.sh.pettopia.Hojji.community.posts.entity.Post;
import com.sh.pettopia.Hojji.community.posts.repository.PostRepository;
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
    private final PostRepository postRepository;

//    // 댓글 등록
//    public CommuCommentResponseDto registComment(Member member, CommuCommentRegistRequestDto commentRegistDto) {
//
//        // 1. postId로 Post 엔티티를 조회합니다.
//        Post post = postRepository.findByPostId(commentRegistDto.getPostId());
//        log.debug("post = {}", post);
//
//        // 2. post와 member의 정보로 Dto -> Entity로 변환합니다.
//        CommunityComment comment = commentRegistDto.toCommunityComment(post, member);
//        log.debug("post와 member가 셋팅된 Comment 엔티티 = {}", comment);
//
////
////        // 2. commentRegistDto를 CommunityComment Entity로 변환하고 Post 객체를 설정합니다.
////        CommunityComment comment = commentRegistDto.toCommunityComment(post);
////        log.debug("Comment 내용 = {}", comment.getCommentContent());
//
//        // 3. Comment Writer를 설정합니다.
////        comment.setWriter(member);
////        log.debug("Comment 작성자 = {}", comment.getMember().getNickName());
//
//        // 4. Comment를 등록합니다.
//        CommunityComment savedComment = commentRepository.save(comment);
//
//        // 5. 저장된 Comment 엔티티를 CommuCommentResponseDto로 변환하여 반환합니다.
//        return CommuCommentResponseDto.fromCommunityComment(savedComment);
//    }

    // 댓글 등록
    public CommuCommentResponseDto registComment(Long postId, Member member, CommuCommentRegistRequestDto commentRegistDto) {

        // 1. postId로 Post 엔티티를 조회합니다.
        Post post = postRepository.findByPostId(postId);
        log.debug("post = {}", post);

        // 2. post와 member의 정보로 Dto -> Entity로 변환합니다.
        CommunityComment comment = commentRegistDto.toCommunityComment(post, member);
        log.debug("post와 member가 셋팅된 Comment 엔티티 = {}", comment);


        // 4. Comment를 등록합니다.
        CommunityComment savedComment = commentRepository.save(comment);

        // 5. 저장된 Comment 엔티티를 CommuCommentResponseDto로 변환하여 반환합니다.
        return CommuCommentResponseDto.fromCommunityComment(savedComment);
    }


    // 게시글 ID로 댓글 목록을 가져오는 메소드입니다.
    public List<CommuCommentResponseDto> findByPostId(Long postId) {
        List<CommunityComment> comments = commentRepository.findByPost_PostId(postId);
        return comments.stream()
                .map(CommuCommentResponseDto::fromCommunityComment)
                .collect(Collectors.toList());
    }

    // 댓글을 삭제하는 메소드입니다.
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

}
