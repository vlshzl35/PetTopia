package com.sh.pettopia.parktj.petsitterfinder.dto;

import com.sh.pettopia.Hojji.pet.entity.*;
import com.sh.pettopia.parktj.petsitterfinder.entity.CareRegistration;
import com.sh.pettopia.parktj.petsitterfinder.entity.RequestService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

/**
 * @Data
 * - 클래스에 필수적인 메소드들을 자동으로 생성해주는 핵심 어노테이션
 * - 모든 필드에 대한 getter 메소드를 생성함
 * - equals()와 hashCode() 메소드를 모든 필드를 사용하여 구현
 * @AllArgsConstructor
 * - 클래스의 모든 필드를 매개변수로 받는 전체 생성자를 자동으로 생성
 *
 * - @NoArgsConstructor
 * - 매개변수가 없는 기본생성자를 생성함
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CareRegistrationListResponseDto {
    private Long petId;
    private String petName;
    private PetSize petSize;
    private PetGender petGender;
    private LocalDate petBirth;
    private boolean isNeutered;
    private String profile;
    private Set<VaccinationType> petVaccinationType;
    private Set<ParasitePrevention> petParasitePrevention;
    private String petSociability;
    private PetStatus isMissing;
    private Long memberId;
    private String petBreed;
    private LocalDate startDate;
    private LocalDate endDate;
    private String address;
    private Set<RequestService> requestService;
    private Long postId;

    /**
     * 8/11 궁금?
     *
     * 받아온 모든 값들을 한곳에 저장하고 그 값들을 어떻게 그 게시글에 맞는것만 보여주는거지?
     *
     * @param careRegistration
     * @return
     */
    public static CareRegistrationListResponseDto fromCareRegistration(CareRegistration careRegistration){
        return CareRegistrationListResponseDto.builder()
                .postId(careRegistration.getPostId())
                .startDate(careRegistration.getRequestStartDate())
                .endDate(careRegistration.getRequestEndDate())
                .petName(careRegistration.getPetName())
                .address(careRegistration.getAddress())
                .requestService(careRegistration.getRequestService())
                .profile(careRegistration.getProfile())
                .build();


    }
}
