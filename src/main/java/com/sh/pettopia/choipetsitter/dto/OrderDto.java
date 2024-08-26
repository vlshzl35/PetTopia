package com.sh.pettopia.choipetsitter.dto;

import com.sh.pettopia.choipetsitter.entity.Order;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDto {
    private String partnerOrderId; //

    private String petSitterId; // 서비스를 실행하는 펫시터 아이디

    private String memberId; // 신청한 회원 아이디

    private LocalDateTime payDate; // 결제 날짜

    private LocalDateTime payCancelDate;

    private boolean payStatus; // true= 결제완료, false=결제취소(환불)

    private int totalPrice; // 금액

    private String tid; // 환불 할 떄 쓰는 고유 번호

    public OrderDto entityToDto(Order entity)
    {
        return OrderDto.builder()
                .partnerOrderId(entity.getPartnerOrderId())
                .payCancelDate(entity.getPayCancelDate())
                .payDate(entity.getPayDate())
                .payStatus(entity.isPayStatus())
                .totalPrice(entity.getTotalPrice())
                .memberId(entity.getMemberId())
                .petSitterId(entity.getPetSitterId())
                .build();
    }
}
