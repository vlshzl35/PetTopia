package com.sh.pettopia.Hojji.community.posts.service;

import com.sh.pettopia.Hojji.community.posts.dto.PostListResponseDto;
import com.sh.pettopia.Hojji.community.posts.entity.Post;
import com.sh.pettopia.Hojji.community.posts.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommunityService {
    private final CommunityRepository communityRepository;

    public Page<PostListResponseDto> findAll(String q, Pageable pageable) {

        // Page 객체에는 Content의 요소를 순회하면서 각 요소를 DTO로 변환할 수 있는 map 메소드를 제공합니다.
        Page<Post> communityPage = q != null ?
                                        communityRepository.findByContent(q, pageable) :
                                        communityRepository.findAll(pageable);
        return communityPage.map(PostListResponseDto::fromPost);
    }
}
