package com.sh.pettopia.choipetsitter.entity;

import com.sh.pettopia.choipetsitter.repository.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

//@SpringBootTest(classes = PetSitterTest.class)
@DataJpaTest
//@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 테스트용 메모리DB 사용안하고 실제 DB 사용
public class PetSitterTest {

    @Autowired
    private PetSitterRepository petSitterRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PetSitterReviewRepository petSitterReviewRepository;

    @Autowired
    private SittingRepository sittingRepository;

    @Autowired
    private PayRepository payRepository;


    @Test
    @DisplayName("update")
    void test1() {
        Map<String , Integer> pet=new HashMap<>();
        pet.put("중형견",1);
        pet.put("대형견",2);
        Reservation reservation= Reservation.builder()
                .petSitterId("cstangga@naver.com")
                .createdAt(LocalDateTime.now())
                .endTime(LocalTime.of(19,0,0))
                .startTime(LocalTime.of(16,0,0))
                .memberId("cstanga@naver.com")
                .reservationDay(
                        List.of(LocalDate.of(2024,8,16),
                                LocalDate.of(2024,8,17),
                                LocalDate.of(2024,8,19))
                        ).note("순합니다")
                .petSizeAndHowMany(pet)
                .reservationStatus(ReservationStatus.OK).build();

        System.out.println("reservation = " + reservation);
    }

}
