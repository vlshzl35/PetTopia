package com.sh.pettopia.parktj.petsitterfinder.entity;

import com.sh.pettopia.Hojji.pet.entity.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
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

    // jpql을 사용하여 pet의 정보를 가져오기 위해 petId 갖고있음
    //JPQL에서는 엔티티 이름을 사용합니다.
    // 객체지향적이고 JPA의 강점을 살리는 방식은 엔티티 간의 관계를 설정하여 Pet 엔티티 간의 직접적인 연관을 맺으라는데

    // 지연로딩, 관련 데이터를 필요할 때까지 미뤄서 가져옴
    // 즉시로딩(EAGER): 설정된 연관관계는 엔티티가 로드될 때 즉시 관련 데이터를 함께 로딩함.

    /**
     * 08/10 @ElementCollection ?
     * - JPA에서 엔티티 클래스의 속성이 기본 값 타입이 컬렉션일 때 사용되는 어노테이션
     * - 엔티티가 아닌 기본 값 타입의 컬렉션을 매핑할 때 사용함
     * - 엔티티가 복합 값 타입(내부적으로 여러 필드를 가지는 객체)을 포함하는 컬렉션을 갖고 있을 때 사용
     * - 복합 값 타입은 일반적으로 @Embeddable로 표시해야하며, @ElementCollection과 함께 사용함
     */

    @Column(name ="pet_id", nullable = false)
    private Long petId;
//
    @Column(name = "member_id", nullable = true)
    private Long memberId;
    
    // @CreationTimestamp - 엔티티가 생성되어 데이터베이스에 처음 저장될 때 현재 시간을 자동으로 해당 필드에 할당
    // 데이터베이스에 엔티티가 처음 저장될 때 한 번만 값이 설정되어야하므로  "updatable = false" 적용, 출처: gpt
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDate createdDate;
    
    // @UpdateTimestamp - 엔티티가 업데이트될 때마다 현재 시간을 해당 필드에 자동으로 할당
    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDate updatedDate;

    // 사용자가 요청하는 서비스, 요청 날짜 이기 때문에, request% 로 통일 하였습니다.
    @Column(name = "request_service", nullable = false)
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "tbl_request_service", joinColumns = @JoinColumn(name = "postId"))
    @Enumerated(EnumType.STRING)
    private Set<RequestService> requestService;

    @Column(name = "reuqest_start_date", nullable = false)
    private LocalDate requestStartDate;

    @Column(name = "request_end_date", nullable = false)
    private LocalDate requestEndDate;


    @Column(name = "pet_name", nullable = false)
    private String petName;

    @Column(name ="pet_bith", nullable = false)
    private LocalDate birth;

    @Column(name ="pet_neutered", nullable = false)
    private boolean neutered;

    @Column(name ="pet_profile_url", nullable = true)
    private String profileUrl;


    @ElementCollection
    @CollectionTable(name = "tbl_pet_vaccination_post", joinColumns = @JoinColumn(name = "postId"))
    private Set<VaccinationType> petVaccinationType;

    @Column(name ="pet_petSociability", nullable = false)
    private String petSociability;

    @Column(name="pet_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private PetStatus status;

    @Column(name = "pet_breed", nullable = false)
    private String breed;

    @Column(name ="pet_size" , nullable = false)
    @Enumerated(EnumType.STRING)
    private PetSize petSize;

    @Column(name = "pet_gender", nullable = false)
    private PetGender petGender;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    @CollectionTable(name = "tbl_pet_parsite_prevention_post", joinColumns = @JoinColumn(name = "postId"))
    private Set<ParasitePrevention> petParasitePrevention;

    @Column(name = "member_address", nullable = false)
    private String address;

/**
 * 08-11 박태준
 * pet_parasite_prevention 이랑 tbl_pet_vaccination은 pet 정보 등록할때랑 다른 테이블에 만들어놔야할듯
 */
}
