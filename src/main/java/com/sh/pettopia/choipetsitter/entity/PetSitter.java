package com.sh.pettopia.choipetsitter.entity;

import com.sh.pettopia.choipetsitter.dto.PetSitterRegisterDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "petsitter")
@Table(name = "tbl_petsitter")
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class PetSitter {
    // 펫 시터의 대한 프로필

    @Id
    @Column(name = "petsitter_id")
    private String petSitterId; // 회원 아이디 = 멤버아이디

    @Column(name = "introduce",columnDefinition = "TEXT")
    private String introduce; // 가벼운 소개

    @Column(name = "one_line_introduce")
    private String oneLineIntroduce; // 리스트에 보여줄 한 줄 소개

    @Column(name = "post_url")
    private String postUrl; // 홍보글 url

    @Column(name = "main_image_url")
    private String mainImageUrl; // 메인 이미지 // 펫시터 리스트 들에서 보여줄 이미지
    // 디테일에서 여러사진들은 ncp에 넣어두고, 필요할 떄 dto에 담아서 model.attribute로 보내자

    @Column(name = "update_at")
    @LastModifiedDate
    private LocalDateTime updateAt;

    @Column(name = "address")
    private PetSitterAddress petSitterAddress; // 주소

    @Column(name = "available_dates")
    @ElementCollection
    private Set<String > availableDates;

    @Column(name = "available_pet_size")
    @Enumerated(EnumType.STRING)
    @ElementCollection
    private Set<AvailablePetSize> availablePetSize;
    // 이용가능한 서비스 ( 빗질, 산책, 약 먹이기, 등등...)

    @Column(name = "available_service")
    @Enumerated(EnumType.STRING)
    @ElementCollection
    private Set<AvailableService> availableService;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    public PetSitter dtoToEntity(PetSitterRegisterDto dto)
    {
        return PetSitter.builder()
                .petSitterId(dto.getPetSitterId())
                .mainImageUrl(dto.getMainImageUrl())
                .availableService(dto.getAvailableServices())
                .availablePetSize(dto.getAvailablePetSizes())
                .introduce(dto.getMainIntroduce())
                .oneLineIntroduce(dto.getOneLineIntroduce())
                .postUrl(dto.getPostUrl())
                .petSitterAddress(dto.getPetSitterAddress())
                .availableDates(dto.getImpossibleDays())
                .build();
    }

    public void changeAvailableDates(Set<String > availableDates )
    {
        this.availableDates=availableDates;
    }
    public void changeAvailableService(Set<AvailableService> availableService)
    {
        this.availableService=availableService;
    }

    public void changeAvailablePetSize(Set<AvailablePetSize> availablePetSize)
    {
        this.availablePetSize=availablePetSize;
    }

    public void changeIntroduce(String introduce)
    {
        this.introduce=introduce;
    }

    public void changeOneLineIntroduce(String oneLineIntroduce)
    {
        this.oneLineIntroduce=oneLineIntroduce;
    }

    public void changeMainImageUrl(String mainImageUrl)
    {
        this.mainImageUrl=mainImageUrl;
    }

    public void changeAddress(String postcode,String address,String detailAddress,String extraAddress)
    {
        this.petSitterAddress= new PetSitterAddress(postcode,address,detailAddress,extraAddress);
    }

}
