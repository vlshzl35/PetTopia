package com.sh.pettopia.Hojji.community.posts.service;

import com.sh.pettopia.Hojji.community.posts.dto.PostListResponseDto;
import com.sh.pettopia.Hojji.community.posts.dto.PostRegistReponseDto;
import com.sh.pettopia.Hojji.community.posts.dto.PostRegistRequestDto;
import com.sh.pettopia.Hojji.community.posts.entity.Post;
import com.sh.pettopia.Hojji.community.posts.repository.PostRepository;
import com.sh.pettopia.Hojji.user.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PostService {
    private final PostRepository postRepository;

    // 커뮤니티 전체 게시글 조회
    public Page<PostListResponseDto> findAll(String q, Pageable pageable) {

        // Page 객체에는 Content의 요소를 순회하면서 각 요소를 DTO로 변환할 수 있는 map 메소드를 제공합니다.
        Page<Post> communityPage = q != null ?
                                        postRepository.findByContent(q, pageable) :
                                        postRepository.findAll(pageable);
        return communityPage.map(PostListResponseDto::fromPost);
    }

    // 하나의 게시글 등록
    public Long registPost(Member member, PostRegistRequestDto postRegistDto) {
        // 1. postRegistDto를 Post 테이블에 저장하기 위해 Post Entity로 변환합니다.
        Post post = postRegistDto.toPost();
        log.debug("Post 제목 = {}", post.getTitle());

        // 2. Post Writer를 설정합니다.
        post.setWriter(member);
        log.debug("Post 작성자 = {}", post.getMember().getNickName());

        // 3. 하나의 게시글 등록합니다.
        Post registPost = postRepository.save(post);

        // 4. 등록된 게시물을 다시 보여주기 위헤 id로 반환합니다.
        return registPost.getPostId();
    }

    // 하나의 게시물 조회
    public PostRegistReponseDto findByPostId(Long postId) {
        // 1. 게시글의 id로 게시글 하나를 조회합니다.
        Post post = postRepository.findByPostId(postId);

        // 2. 조회된 게시물을 다시 ResponseDto로 변환합니다.
        return PostRegistReponseDto.fromPost(post);
    }
}
