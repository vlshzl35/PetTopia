package com.sh.pettopia.parktj.petsitterfinder.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

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
    private Long postId;

    // jpql을 사용하여 pet의 정보를 가져오기 위해 petId 갖고있음
    //JPQL에서는 엔티티 이름을 사용합니다.
    // 객체지향적이고 JPA의 강점을 살리는 방식은 엔티티 간의 관계를 설정하여 Pet 엔티티 간의 직접적인 연관을 맺으라는데

    // 지연로딩, 관련 데이터를 필요할 때까지 미뤄서 가져옴
    // 즉시로딩(EAGER): 설정된 연관관계는 엔티티가 로드될 때 즉시 관련 데이터를 함께 로딩함.

    @Column(name = "member_id", nullable = false )
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
    @Enumerated(EnumType.STRING)
    private RequestService requestService;

    @Column(name = "reuqest_start_date", nullable = false)
    private LocalDate requestStartDate;

    @Column(name = "request_end_date", nullable = false)
    private LocalDate requestEndDate;

    @Column(name = "dog_image_url", nullable = false)
    private String dogImangeUrl;


}
