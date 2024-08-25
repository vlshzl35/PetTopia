package com.sh.pettopia.parktj.petsitterfinder.entity;

import com.sh.pettopia.Hojji.pet.entity.*;
import com.sh.pettopia.parktj.petsitterfinder.dto.PetDetailsUpdateRequestDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
@Entity(name = "careregistration")
@Table(name = "tbl_care_registration")
@Data
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CareRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;
    // 어떤 펫을 맡기는지 알기 위해
    @Column(name = "pet_id", nullable = false)
    private Long petId;
    //어떤 회원의 게시글인지 알기위해
    @Column(name = "member_id", nullable = true)
    private Long memberId;

    // @CreationTimestamp - 엔티티가 생성되어 데이터베이스에 처음 저장될 때 현재 시간을 자동으로 해당 필드에 할당
    // 데이터베이스에 엔티티가 처음 저장될 때 한 번만 값이 설정되어야하므로  "updatable = false" 적용
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDate createdDate;

    // @UpdateTimestamp - 엔티티가 업데이트될 때마다 현재 시간을 해당 필드에 자동으로 할당
    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDate updatedDate;

    // 사용자가 요청하는 서비스, 요청 날짜 이기 때문에, request% 로 통일 하였습니다.
    /**
     * @ElementCollection : 기본 값 타입을 갖는 컬렉션을 매핑할 때 사용되는 JPA 어노테이션
     * - 값 타입은 엔티티가 아닌 타입을 의미함. 기본적으로 int, String , 열거형
     * @CollectionTable : 컬렉션이 매핑될 데이터베이스 테이블의 세부사항을 지정함
     * - name 속성은 컬렉션이 저장될 테이블 이름을 지정
     * - joinColums 속성은 컬렉션이 소유 엔티티와 연결될 떄 조인되는 조인 컬럼을 지정 여기서는 postId컬럼
     */
    @ElementCollection
    @CollectionTable(name = "tbl_request_service", joinColumns = @JoinColumn(name = "post_id"))
    @Enumerated(EnumType.STRING)
    private Set<RequestService> requestService;

    @Column(name = "reuqest_start_date", nullable = false)
    private LocalDate requestStartDate;

    @Column(name = "request_end_date", nullable = false)
    private LocalDate requestEndDate;


    @Column(name = "pet_name", nullable = false)
    private String petName;

    @Column(name = "pet_bith", nullable = false)
    private LocalDate birth;

    @Column(name = "pet_neutered", nullable = false)
    private boolean neutered;

    @Column(name = "pet_profile_url", nullable = true)
    private String profile;

    @ElementCollection
    @CollectionTable(name = "tbl_pet_vaccination_post", joinColumns = @JoinColumn(name = "postId"))
    private Set<VaccinationType> petVaccinationType;

    @Column(name = "pet_petSociability", nullable = false)
    private String petSociability;
    // 실종 혹은 실종아님
    @Column(name = "pet_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private PetStatus status;
    // 견종
    @Column(name = "pet_breed", nullable = false)
    private String breed;
    // 중형견, 소형견, 대형견
    @Column(name = "pet_size", nullable = false)
    @Enumerated(EnumType.STRING)
    private PetSize petSize;
    // 성별
    @Column(name = "pet_gender", nullable = false)
    private PetGender petGender;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    @CollectionTable(name = "tbl_pet_parsite_prevention_post", joinColumns = @JoinColumn(name = "postId"))
    private Set<ParasitePrevention> petParasitePrevention;

    @Column(name = "member_address", nullable = false)
    private String address;

    @Column(name ="additional_info")
    private String additionalInfo;


    public void update(PetDetailsUpdateRequestDto dto) {
        this.postId = dto.getPostId();
        this.address = dto.getAddress();
        this.requestStartDate = dto.getRequestStartDate();
        this.requestEndDate = dto.getRequestEndDate();
        this.requestService = dto.getRequestService();
    }

    public void toStringVaccinationName(Set<String> strings)
    {

    }

}
