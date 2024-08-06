package com.sh.pettopia.enterprise.entity;

import com.sh.pettopia.enterprise.repository.EnterpriseRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static groovyjarjarantlr4.v4.gui.Trees.save;
import static java.time.LocalTime.now;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
//@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 테스트용 메모리DB 사용안하고 실제 DB(여기선 tbl_menu) 사용
public class EnterpriseTest {
    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Test
    @DisplayName("Create / EnterpriseRepository 구현클래스의 빈을 의존주입 받는다.")
    void test1() {
        // given
        // when
        // then
        assertThat(enterpriseRepository).isNotNull();
        System.out.println(enterpriseRepository); // org.springframework.data.jpa.repository.support.SimpleJpaRepository@12f32f99
        /*
            Hibernate:
                create table hibernate_sequences (
                    next_val bigint,
                    sequence_name varchar(255) not null,
                    primary key (sequence_name)
                ) engine=InnoDB
            Hibernate:
                create table tbl_hospital (
                    ent_id integer not null,
                    ent_address varchar(255) not null,
                    ent_name varchar(255) not null,
                    ent_phone varchar(255) not null,
                    office_hours varchar(255) not null,
                    primary key (ent_id)
                ) engine=InnoDB
            Hibernate:
                create table tbl_pharmacy (
                    ent_id integer not null,
                    ent_address varchar(255) not null,
                    ent_name varchar(255) not null,
                    ent_phone varchar(255) not null,
                    office_hours varchar(255) not null,
                    primary key (ent_id)
                ) engine=InnoDB
            Hibernate:
                create table tbl_salon (
                    ent_id integer not null,
                    ent_address varchar(255) not null,
                    ent_name varchar(255) not null,
                    ent_phone varchar(255) not null,
                    office_hours varchar(255) not null,
                    primary key (ent_id)
                ) engine=InnoDB
            Hibernate:
                    create table tbl_review (
                    idx integer not null,
                    payment_date date,
                    review_id integer not null,
                    total_price integer,
                    created_at datetime(6),
                    updated_at datetime(6),
                    business_registration varchar(255),
                    ent_name varchar(255),
                    receipt_img_url varchar(255),
                    review_content varchar(255),
                    rating enum ('FIVE','FOUR','ONE','THREE','TWO'),
                    primary key (idx, review_id)
                ) engine=InnoDB
         */
    }

    @Test
//    @DisplayName("Hospital, Pharmacy, Salon 엔티티 / Review vo등록")
    @DisplayName("병원 등록")
    @Rollback(value = false) // 테스트 후 데이터 롤백 방지
    void test2() {
        // given
        Receipt receipt = new Receipt("1234","동물병원1", LocalDate.now(),55000,"re@naver.com");
        List<Review> entReviews = List.of(
                new Review(Rating.ONE, "가격이 비싸다", LocalDateTime.now(), LocalDateTime.now(), receipt),
                new Review(Rating.TWO, "ㅈㄴ어렵다", LocalDateTime.now(), LocalDateTime.now(), receipt),
                new Review(Rating.THREE, "인생 엿같다", LocalDateTime.now(), LocalDateTime.now(), receipt)
        );
        Hospital hospital = new Hospital(0,"동물병원1","1234-5678","서울시 강남구","09:00-18:00",entReviews);
        // when
        hospital = enterpriseRepository.save(hospital);
//        enterpriseRepository.flush(); // insert 쿼리 강제 실행
        System.out.println(hospital);
        // then
        assertThat(hospital.getEntId()).isNotZero();
    }

    @Test
    @DisplayName("select 쿼리")
    void test3() {
        // given
        // when
        // then
    }

    @Test
    @DisplayName("update 쿼리")
    void test4() {
        // given
        // when
        // then
    }

    @Test
    @DisplayName("delete 쿼리")
    void test5() {
        // given
        // when
        // then
    }
}

