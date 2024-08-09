package com.sh.pettopia.choipetsitter.entity;

import com.sh.pettopia.choipetsitter.repository.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
//        Set<AvailablePetSize> petSizes=new HashSet<>();
//        petSizes.add(AvailablePetSize.대형견);
//        petSizes.add(AvailablePetSize.중형견);
//
//        List<String> imageUrls=new ArrayList<>();
//        imageUrls.add("이미지1 주소입니다");
//        imageUrls.add("이미지2 주소입니다");
//        imageUrls.add("이미지3 주소입니다");
//        imageUrls.add("이미지4 주소입니다");
//
//
//
//        Set<AvailableService> services=new HashSet<>();
//        services.add(AvailableService.매일산책);
//        services.add(AvailableService.응급처치);
//        services.add(AvailableService.노견산책);
//        services.add(AvailableService.모발관리);
//        PetSitterAddress petSitterAddress= new PetSitterAddress("05234","서울 강동구 고덕로61길 37","102동 1006","(고덕동, 현대아파트)");
//
//        PetSitter petSitter=PetSitter.builder()
//                .petSitterId("cstangga@naver.com")
//                .availablePetSize(petSizes)
//                .availableService(services)
//                .imagesUrlList(imageUrls)
//                .petSitterAddress(petSitterAddress)
//                .introduce("안녕하세요 최최창창욱욱입니다")
//                .url("www.daum.com").build();
//
//        petSitterRepository.save(petSitter);
//
//
//        System.out.println("petSitter = " + petSitter);
    }

}
