package com.sh.pettopia.parktj.petsitterfinder.dto;
import com.sh.pettopia.Hojji.pet.entity.*;
import com.sh.pettopia.Hojji.user.member.entity.Member;
import com.sh.pettopia.parktj.petsitterfinder.entity.RequestService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

// 게시글 등록 폼에서 입력된 값을 서버로 전달하여 처리하고자 할 때는 "요청" 데이터를 담고 있기 때문에, 이 경우에는 Request DTO가 적당하다.

// 반대로 , ResponseDTO는 서버에서 처리한 결과를 클라이언트에게 다시 전달할 때 사용된다. 예를 들어, 사용자가 요청한 데이터 처리 후 그 결과를 클라이언트에게 보여주기 위해 사용되는 객체임.

// 이 DTO 는 등록폼에서 등록된 정보들을 등록 글 리스트 상세보기에 뿌려주기 위한 역할의 DTO이다.
@AllArgsConstructor
@Data
@NoArgsConstructor
public class CareRegistrationRequestDto {
    private Long petId;
    private String petName;
    private int age;
    private int weight;
    private PetGender petGender;
    private LocalDate birth;
    private boolean neutered;
    private String profile;
    private Set<VaccinationType> vaccinationType;
    private Set<ParasitePrevention> parasitePrevention;
    private String socialization;
    private PetStatus status;
    private Member member;

    // 아 따로따로 만들어야 하나보다 responseDto 게시글에서 받아올거랑 pet에서 받아올거 8/7 박태준
//    private LocalDate createdDate;
//    private LocalDate updatedDate;
//    private RequestService requestService;
//    private LocalDate requestStartDate;
//    private LocalDate requestEndDate;
//    private String dogImageUrl;

    /**
     * Pet Entity를 DTO로 변환하는 STATIC 메소드
     * @param pet
     * @return
     */

    public static CareRegistrationRequestDto fromPet(Pet pet){
        return new CareRegistrationRequestDto(
                pet.getPetId(),
                pet.getName(),
                pet.getAge(),
                pet.getWeight(),
                pet.getPetGender(),
                pet.getBirth(),
                pet.isNeutered(),
                pet.getProfile(),
                pet.getVaccinationType(),
                pet.getParasitePrevention(),
                pet.getSocialization(),
                pet.getStatus(),
                pet.getMember());
    }
}



