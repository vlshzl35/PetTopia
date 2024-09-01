package com.sh.pettopia.Hojji.user.member.repository;

import com.sh.pettopia.Hojji.user.member.entity.Gender;
import com.sh.pettopia.Hojji.user.member.entity.Authority;
import com.sh.pettopia.Hojji.user.member.entity.Member;
import com.sh.pettopia.Hojji.user.member.entity.SitterStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 테스트용 메모리DB 사용안하고 실제 DB 사용replace = Auto)
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("MemberRepository 구현클래스의 빈을 의존 주입 받는다.")
    void test1() {
        /**
         * create table tbl_member (
         *         birth date,
         *         created_at date,
         *         id bigint not null,
         *         address varchar(255),
         *         email varchar(255),
         *         name varchar(255),
         *         nick_name varchar(255),
         *         password varchar(255),
         *         phone varchar(255),
         *         profile_image varchar(255),
         *         gender enum ('F','M'),
         *         sitter_status enum ('APPROVED','NONE','PENDING'),
         *         primary key (id)
         *     ) engine=InnoDB
         */
    }

    @Test
    @DisplayName("일반 회원/펫시터/관리자 등록")
    @Transactional
//    @Rollback(value = false) // @DataJpaTest가 기본적으로 테스트 후, 트랜잭션을 롤백하므로 실제 DB에 저장하기 위해 사용합니다.
    public void test(){
        //given
        Member member = Member.builder()
                .email("jmh907@gmail.comm")
                .password("123")
                .name("홍희망")
                .gender(Gender.F)
                .phone("010")
                .authorities(Set.of(Authority.ROLE_MEMBER, Authority.ROLE_ADMIN, Authority.ROLE_SITTER))
                .nickName("희망읭잉")
                .address("강남구")
                .profileImage("희망.jpg")
                .birth(LocalDate.of(1999,9,7))
                .createdAt(LocalDate.now())
                .sitterStatus(SitterStatus.NONE)
                .build();

        //when
        member = memberRepository.saveAndFlush(member); // insert문 바로 날리기 위함
        /**
         * Hibernate:
         *     select
         *         tbl.next_val
         *     from
         *         hibernate_sequences tbl
         *     where
         *         tbl.sequence_name=? for update
         * Hibernate:
         *     update
         *         hibernate_sequences
         *     set
         *         next_val=?
         *     where
         *         next_val=?
         *         and sequence_name=?
         * Hibernate:
         *     insert
         *     into
         *         tbl_member
         *         (email, gender, name, password, phone, address, birth, created_at, nick_name, profile_image, sitter_status, id)
         *     values
         *         (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
         * Hibernate:
         *     insert
         *     into
         *         tbl_authority
         *         (member_id, authorities)
         *     values
         *         (?, ?)
         * Hibernate:
         *     insert
         *     into
         *         tbl_authority
         *         (member_id, authorities)
         *     values
         *         (?, ?)
         * Hibernate:
         *     insert
         *     into
         *         tbl_authority
         *         (member_id, authorities)
         *     values
         *         (?, ?)
         */
        System.out.println(member);
        /**
         * Member(super=User(id=52, email=jmh907@gmail.comm, password=123, name=홍희망, gender=F, phone=010, authorities=[ROLE_SITTER, ROLE_ADMIN, ROLE_MEMBER]), nickName=희망읭잉, address=강남구, profileImage=희망.jpg, birth=1999-09-07, createdAt=2024-08-11, sitterStatus=NONE)
         */

        //then
        assertThat(member.getId()).isNotZero();
    }

    @Test
    @DisplayName("관리자 등록")
    @Transactional
    @Rollback(value = false) // @DataJpaTest가 기본적으로 테스트 후, 트랜잭션을 롤백하므로 실제 DB에 저장하기 위해 사용합니다.
    public void test2(){
        //given
        Member member = Member.builder()
                .email("jmh907@gmail.comm")
                .password("123")
                .name("관리쟈?")
                .gender(Gender.F)
                .phone("010")
                .authorities(Set.of(Authority.ROLE_ADMIN))
                .nickName("관리쟈라ㅏㅏㅏ")
                .address("강남구")
                .profileImage("관리쟝.jpg")
                .birth(LocalDate.of(1999,9,7))
                .createdAt(LocalDate.now())
                .sitterStatus(SitterStatus.NONE)
                .build();

        //when
        member = memberRepository.saveAndFlush(member); // insert문 바로 날리기 위함
        /**
         * Hibernate:
         *     select
         *         tbl.next_val
         *     from
         *         hibernate_sequences tbl
         *     where
         *         tbl.sequence_name=? for update
         * Hibernate:
         *     update
         *         hibernate_sequences
         *     set
         *         next_val=?
         *     where
         *         next_val=?
         *         and sequence_name=?
         * Hibernate:
         *     insert
         *     into
         *         tbl_member
         *         (email, gender, name, password, phone, address, birth, created_at, nick_name, profile_image, sitter_status, id)
         *     values
         *         (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
         * Hibernate:
         *     insert
         *     into
         *         tbl_authority
         *         (member_id, authorities)
         *     values
         *         (?, ?)
         */
        System.out.println(member);
        /**
         * Member(super=User(id=152, email=jmh907@gmail.comm, password=123, name=관리쟈?, gender=F, phone=010, authorities=[ROLE_ADMIN]), nickName=관리쟈라ㅏㅏㅏ, address=강남구, profileImage=관리쟝.jpg, birth=1999-09-07, createdAt=2024-08-13, sitterStatus=NONE)
         */

        //then
        assertThat(member.getId()).isNotZero();
    }
}