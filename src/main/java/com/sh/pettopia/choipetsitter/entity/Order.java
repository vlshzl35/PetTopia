package com.sh.pettopia.choipetsitter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Entity(name = "order")
@Table(name = "tbl_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @Id
    @Column(name = "partner_order_id")
    private String partnerOrderId; //

    @Column(name = "petsitter_id")
    private String petSitterId; // 서비스를 실행하는 펫시터 아이디

    @Column(name = "member_id")
    private String memberId; // 신청한 회원 아이디

    @Column(name = "pay_date")
    @CreationTimestamp
    private LocalDateTime payDate; // 결제 날짜

    @Column(name = "pay_status")
    private boolean payStatus; // true= 결제완료, false=결제취소(환불)

    @Column(name = "total_price")
    private int totalPrice; // 금액

    @Column(name = "tid")
    private String tid; // 환불 할 떄 쓰는 고유 번호

    public Order reservationToOrder(Reservation reservation)
    {
        return Order.builder()
                .partnerOrderId(reservation.getPartnerOrderId())
                .memberId(reservation.getMemberId())
                .payDate(LocalDateTime.now())
                .payStatus(true)
                .totalPrice(reservation.getTotalPrice())
                .petSitterId(reservation.getPetSitterId())
                .build();
    }

}
