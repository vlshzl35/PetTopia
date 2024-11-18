//package com.sh.pettopia.mypage.repository;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataJpaTest // jpa관련 빈만 로드 @SpringBootTest 대비 가볍다.
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//class MyPageRepositoryTest {
//    @Autowired
//    private MyPageRepository myPageRepository;
//
//    @Test
//    @DisplayName("MyPageRepository 구현클래스의 빈을 의존주입 받는다.")
//    void test1() {
//        // given
//        // when
//        // then
//        assertThat(myPageRepository).isNotNull();
//        System.out.println(myPageRepository);
//    }
//
//
//}