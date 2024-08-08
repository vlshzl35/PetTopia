//package com.sh.pettopia.parktj.petsitterfinder.repository;
//
//import com.sh.pettopia.Hojji.pet.entity.*;
//import com.sh.pettopia.parktj.petsitterfinder.entity.CareRegistration;
//import com.sh.pettopia.parktj.petsitterfinder.entity.RequestService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.repository.query.Param;
//
//import java.time.LocalDate;
//import java.util.Set;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest
//class PetsitterCareRegistrationRepositoryTest {
//
//    @Autowired
//    private PetsitterCareRegistrationRepository petsitterCareRegistrationRepository;
//
//    @BeforeEach
//    public void setUp() {
//        // 테스트 데이터 준비
//        Pet pet = Pet.builder()
//                .name("희망이")
//                .age(2)
//                .weight(10)
//                .petGender(PetGender.M)
//                .birth(LocalDate.of(2021, 1, 1))
//                .neutered(true)
//                .vaccinationType(Set.of(VaccinationType.RABIES))
//                .parasitePrevention(Set.of(ParasitePrevention.HEARTWORM))
//                .socialization("Good")
//                .status(PetStatus.MISSING)
//                .build();
//
//
//        CareRegistration careRegistration = CareRegistration.builder()
//                .pet(pet)
//                .memberId(1L)
//                .requestService(RequestService.노견산책케어)
//                .requestStartDate(LocalDate.now())
//                .requestEndDate(LocalDate.now().plusDays(5))
//                .dogImangeUrl("http://example.com/dog.jpg")
//                .build();
//
//    }
//
//    @Test
//    public void testFindWithPetByPostId() {
//        // Given
//        Long postId = 1L; // 존재하는 postId로 변경
//
//        // When
//        CareRegistration careRegistration = petsitterCareRegistrationRepository.findWithPetByPostId(postId);
//
//        // Then
//        assertThat(careRegistration).isNotNull();
//        assertThat(careRegistration.getPet()).isNotNull();
//        assertThat(careRegistration.getPet());
//    }
//}