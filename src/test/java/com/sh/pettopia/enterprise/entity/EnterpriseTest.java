//package com.sh.pettopia.enterprise.entity;
//
//import com.sh.pettopia.enterprise.repository.EnterpriseRepository;
//import com.sh.pettopia.enterprise.repository.ReviewRepository;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.annotation.Rollback;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@DataJpaTest
////@SpringBootTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 테스트용 메모리DB 사용안하고 실제 DB 사용
//public class EnterpriseTest {
//
//    @Autowired
//    private EnterpriseRepository enterpriseRepository;
//    @Autowired
//    private ReviewRepository reviewRepository;
//
//    @Test
//    @DisplayName("Create / EnterpriseRepository 구현클래스의 빈을 의존주입 받는다.")
//    void test1() {
//        // given
//        // when
//        // then
//        assertThat(enterpriseRepository).isNotNull();
//        System.out.println(enterpriseRepository); // org.springframework.data.jpa.repository.support.SimpleJpaRepository@12f32f99
//        /*
//        Hibernate:
//            create table tbl_hospital (
//                ent_id bigint not null,
//                ent_address varchar(255) not null,
//                ent_name varchar(255) not null,
//                ent_phone varchar(255) not null,
//                office_hours varchar(255) not null,
//                primary key (ent_id)
//            ) engine=InnoDB
//        Hibernate:
//            create table tbl_pharmacy (
//                ent_id bigint not null,
//                ent_address varchar(255) not null,
//                ent_name varchar(255) not null,
//                ent_phone varchar(255) not null,
//                office_hours varchar(255) not null,
//                primary key (ent_id)
//            ) engine=InnoDB
//        Hibernate:
//            create table tbl_salon (
//                ent_id bigint not null,
//                ent_address varchar(255) not null,
//                ent_name varchar(255) not null,
//                ent_phone varchar(255) not null,
//                office_hours varchar(255) not null,
//                primary key (ent_id)
//            ) engine=InnoDB
//        Hibernate:
//            create table tb_ent_review (
//                payment_date date,
//                total_price integer,
//                created_at datetime(6),
//                ent_id bigint,
//                member_id bigint,
//                review_id bigint not null auto_increment,
//                updated_at datetime(6),
//                business_registration varchar(255),
//                ent_name varchar(255),
//                receipt_img_url varchar(255),
//                review_content varchar(255),
//                rating enum ('FIVE','FOUR','ONE','THREE','TWO'),
//                primary key (review_id)
//            ) engine=InnoDB
//         */
//
//    }
//
//    @Test
//    @DisplayName("업체 등록")
//    @Rollback(value = false) // 테스트 후 데이터 롤백 방지
//    void test2() {
//        // given
//        Hospital hospital = new Hospital(0L,"동물병원1","1234-5678","서울시 강남구","09:00-18:00");
//        Hospital hospital2 = new Hospital(0L,"동물병원2","1234-5678","서울시 강남구","09:00-18:00");
//        Hospital hospital3 = new Hospital(0L,"동물병원3","1234-5678","서울시 강남구","09:00-18:00");
//        Pharmacy pharmacy1 = new Pharmacy(0L,"약국1","1234-5678","서울시 강남구","09:00-18:00");
//        Salon Salon1 = new Salon(0L,"미용실1","1234-5678","서울시 강남구","09:00-18:00");
//
//        // when
//        hospital = enterpriseRepository.save(hospital);
//        hospital2 = enterpriseRepository.save(hospital2);
//        Salon1 = enterpriseRepository.save(Salon1);
//        pharmacy1 = enterpriseRepository.save(pharmacy1);
//        hospital3 = enterpriseRepository.save(hospital3);
//
//        // then
//        assertThat(hospital.getEntId()).isNotZero();
//
//        /*
//        Hibernate:
//                select
//                h1_0.ent_id,
//                h1_0.ent_address,
//                h1_0.ent_name,
//                h1_0.ent_phone,
//                h1_0.office_hours
//            from
//                tbl_hospital h1_0
//            where
//                h1_0.ent_id=?
//        Hibernate:
//            select
//                tbl.next_val
//            from
//                hibernate_sequences tbl
//            where
//                tbl.sequence_name=? for update
//        Hibernate:
//            update
//                hibernate_sequences
//            set
//                next_val=?
//            where
//                next_val=?
//                and sequence_name=?
//        Hibernate:
//            select
//                h1_0.ent_id,
//                h1_0.ent_address,
//                h1_0.ent_name,
//                h1_0.ent_phone,
//                h1_0.office_hours
//            from
//                tbl_hospital h1_0
//            where
//                h1_0.ent_id=?
//        Hibernate:
//            select
//                s1_0.ent_id,
//                s1_0.ent_address,
//                s1_0.ent_name,
//                s1_0.ent_phone,
//                s1_0.office_hours
//            from
//                tbl_salon s1_0
//            where
//                s1_0.ent_id=?
//        Hibernate:
//            select
//                p1_0.ent_id,
//                p1_0.ent_address,
//                p1_0.ent_name,
//                p1_0.ent_phone,
//                p1_0.office_hours
//            from
//                tbl_pharmacy p1_0
//            where
//                p1_0.ent_id=?
//        Hibernate:
//            select
//                h1_0.ent_id,
//                h1_0.ent_address,
//                h1_0.ent_name,
//                h1_0.ent_phone,
//                h1_0.office_hours
//            from
//                tbl_hospital h1_0
//            where
//                h1_0.ent_id=?
//        Hibernate:
//            insert
//            into
//                tbl_hospital
//                (ent_address, ent_name, ent_phone, office_hours, ent_id)
//            values
//                (?, ?, ?, ?, ?)
//        Hibernate:
//            insert
//            into
//                tbl_hospital
//                (ent_address, ent_name, ent_phone, office_hours, ent_id)
//            values
//                (?, ?, ?, ?, ?)
//        Hibernate:
//            insert
//            into
//                tbl_salon
//                (ent_address, ent_name, ent_phone, office_hours, ent_id)
//            values
//                (?, ?, ?, ?, ?)
//        Hibernate:
//            insert
//            into
//                tbl_pharmacy
//                (ent_address, ent_name, ent_phone, office_hours, ent_id)
//            values
//                (?, ?, ?, ?, ?)
//        Hibernate:
//            insert
//            into
//                tbl_hospital
//                (ent_address, ent_name, ent_phone, office_hours, ent_id)
//            values
//                (?, ?, ?, ?, ?)
//        */
//    }
//
////    @Test
////    @DisplayName("리뷰/영수증 등록")
////    @Rollback(value = false) // 테스트 후 데이터 롤백 방지
////    void test3() {
////        // given
////        Receipt receipt = new Receipt("1234","동물병원1", LocalDate.now(),55000,"re@naver.com");
////
////        Hospital hospital = new Hospital(0L,"동물병원1","1234-5678","서울시 강남구","09:00-18:00");
////        // when
////        hospital = enterpriseRepository.save(hospital);
//////        enterpriseRepository.flush(); // insert 쿼리 강제 실행
////        System.out.println(hospital);
////        // then
////        assertThat(hospital.getEntId()).isNotZero();
////    }
//
//    @Test
//    @DisplayName("리뷰/ 영수증 생성")
//    @Rollback(false) // 트랜잭션 롤백 비활성화
//    void test3() {
//        // given
//        Receipt receipt1 = new Receipt("1234","동물병원1", LocalDate.now(),55000,"re@naver.com");
//        Review review1 = new Review(0L,102L,1L,Rating.FIVE,"너무 비싸요",LocalDateTime.now(), LocalDateTime.now(), receipt1);
//        Receipt receipt2 = new Receipt("1111","미용실1", LocalDate.now(),30000,"salon@naver.com");
//        Review review2 = new Review(0L,104L,1L,Rating.FIVE,"커트 맛집이요",LocalDateTime.now(), LocalDateTime.now(), receipt1);
//        // when
//        review1 = reviewRepository.save(review1);
//        review2 = reviewRepository.save(review2);
//
//        // then
//        assertNotNull(review1.getReviewId());
//        assertNotNull(review2.getReviewId());
//    }
//
//    @Test
//    @DisplayName("select 쿼리")
//    void test4() {
//        // given
//        // when
//        // then
//    }
//
//    @Test
//    @DisplayName("update 쿼리")
//    void test5() {
//        // given
//        // when
//        // then
//    }
//
//    @Test
//    @DisplayName("delete 쿼리")
//    void test6() {
//        // given
//        // when
//        // then
//    }
//}
//
