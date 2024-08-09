package com.sh.pettopia.Hojji.user.member.repository;

import com.sh.pettopia.Hojji.user.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("""
        select
            m
        from
            Member m join fetch m.authorities
        where
            m.email = :username
    """)
    Optional<Member> findByUsername(String username);

    boolean existsByEmail(String email);
}
