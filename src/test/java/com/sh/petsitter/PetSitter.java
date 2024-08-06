package com.sh.petsitter;

import jakarta.persistence.*;
import lombok.*;

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
    private String  petSitterId; // 회원의 아이디이다

    @Column(name = "introduce")
    private String introduce; // 가벼운 소개

    @Column(name ="url")
    private String url; // 홍보글 url

    @Column(name = "images_url_list")
    @ElementCollection
    private List<String> images_url_list;

    // 시터가능한 반려견 사이즈 (대,중,소)

    @Column(name = "available_pet_size")
    @Enumerated(EnumType.STRING)
    @ElementCollection
    private Set<AvailablePetSize> availablePetSize;
    // 이용가능한 서비스 ( 빗질, 산책, 약 먹이기, 등등...)

    @Column(name = "available_service")
    @Enumerated(EnumType.STRING)
    @ElementCollection
    private Set<AvailableService> availableService;

    @Embedded
    private PetSitterAddress petSitterAddress;

    public void changeIntroduce(String introduce)
    {
        this.introduce=introduce;
    }

    public void changeAvailablePetSize(AvailablePetSize availablePetSize)
    {
        this.availablePetSize.add(availablePetSize);
    }

    public void removeAvailablePetSize(AvailablePetSize availablePetSize)
    {
        this.availablePetSize.remove(availablePetSize);
    }

    public void changeAvailableService(AvailableService availableService)
    {
        this.availableService.add(availableService);
    }

    public void removeAvailableService(AvailableService availableService)
    {
        this.availableService.remove(availableService);
    }

    public void dtoToEntity(String introduce, String url, List<String> images_url_list, Set<AvailablePetSize> availablePetSize, Set<AvailableService> availableService,
                            PetSitterAddress petSitterAddress) {
        this.introduce = introduce;
        this.url = url;
        this.images_url_list = images_url_list;
        this.availablePetSize = availablePetSize;
        this.availableService = availableService;
        this.petSitterAddress=petSitterAddress;
    }

}
