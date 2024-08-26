package com.sh.pettopia.Hojji.user.member.repository;

import com.sh.pettopia.Hojji.user.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
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
    Optional<Member> findByUsername(@Param("username")String username);

    boolean existsByEmail(String email);

    @Query("""
        select 
            distinct m 
        from 
            Member m join m.authorities a 
        where 
            a = 'ROLE_SITTER'
    """)
    List<Member> findAllSitterMembers();
}
