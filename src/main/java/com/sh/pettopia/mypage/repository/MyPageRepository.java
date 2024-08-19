package com.sh.pettopia.mypage.repository;

import com.sh.pettopia.Hojji.user.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MyPageRepository extends JpaRepository<Member, Long> {
}