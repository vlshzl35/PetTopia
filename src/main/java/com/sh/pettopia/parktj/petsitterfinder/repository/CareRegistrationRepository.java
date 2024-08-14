package com.sh.pettopia.parktj.petsitterfinder.repository;

import com.sh.pettopia.parktj.petsitterfinder.entity.CareRegistration;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CareRegistrationRepository extends JpaRepository<CareRegistration, Long> {

    // CareRegistration Entity postId 큰순부터 받기위한것, 최근에 등록된 게시물이 제일 앞에 뜨게 하기 위해
    List<CareRegistration> findAllByOrderByPostIdDesc();

    CareRegistration findAllByPostId(Long postId);

    void deleteByPostId(Long postId);

//    void updateByPostIdAndMemberId(Long postId, Long memberId);
}
