package com.sh.pettopia.choipetsitter.entity;

import com.sh.pettopia.choipetsitter.dto.PetSitterRegisterDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity(name = "petsitter")
@Table(name = "tbl_petsitter")
@Data
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetSitter {
    // 펫 시터의 대한 프로필



    @Id
    @Column(name = "petsitter_id")
    private String petSitterId; // 회원 아이디 = 멤버아이디

    @Column(name = "introduce")
    private String introduce; // 가벼운 소개

    @Column(name = "post_url")
    private String postUrl; // 홍보글 url

    @Column(name = "images_url_list")
    @ElementCollection
    private List<String> imagesUrlList;

    // 시터가능한 반려견 사이즈 (대,중,소)

    @Column(name = "available_pet_size")
    @Enumerated(EnumType.STRING)
    @ElementCollection
    private Set<AvailablePetSize> availablePetSize;
    // 이용가능한 서비스 ( 빗질, 산책, 약 먹이기, 등등...)

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    @UpdateTimestamp
    private LocalDateTime updateAt;

    @Column(name = "available_service")
    @Enumerated(EnumType.STRING)
    @ElementCollection
    private Set<AvailableService> availableService;

    @Embedded
    private PetSitterAddress petSitterAddress;

    public void changeIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public void changeAvailablePetSize(AvailablePetSize availablePetSize) {
        this.availablePetSize.add(availablePetSize);
    }

    public void removeAvailablePetSize(AvailablePetSize availablePetSize) {
        this.availablePetSize.remove(availablePetSize);
    }

    public void changeAvailableService(AvailableService availableService) {
        this.availableService.add(availableService);
    }

    public void removeAvailableService(AvailableService availableService) {
        this.availableService.remove(availableService);
    }

    public PetSitter DtoToEntity(PetSitterRegisterDto dto)
    {
        return PetSitter.builder()
                .petSitterId(dto.getMemberId())
                .imagesUrlList(dto.getImageUrlList())
                .availableService(dto.getAvailableServices())
                .availablePetSize(dto.getAvailablePetSizes())
                .introduce(dto.getIntroduce())
                .postUrl(dto.getPostUrl())
                .petSitterAddress(dto.getPetSitterAddress()).build();
    }

}
