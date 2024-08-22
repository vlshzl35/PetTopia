package com.sh.pettopia.enterprise.dto;

import com.sh.pettopia.enterprise.entity.Receipt;
import com.sh.pettopia.enterprise.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRegistDto {
    private Long entId;
    private Long userId; // Session에서 가져오기
    private int rating; // 별점
    private String reviewContent; // 내용

    // ReceiptVo 필드
    private String bizNum; // 사업자번호
    private String entName; // 업체명
    private String paymentDate; // 결제일자
    private String totalPrice; // 총 결제금액


    // ReviewRegistDto -> Review 엔티티 변환
    public Review toReview(){
        LocalDate convertedPaymentDate = null;

        // 날짜 형식을 위한 포맷터 정의
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.KOREAN);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.KOREAN);
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy년 M월 d일", Locale.KOREAN);

        try {
            // 첫 번째 포맷으로 시도합니다 (yyyy-MM-dd)
            convertedPaymentDate = LocalDate.parse(this.paymentDate, formatter1);
        } catch (Exception e1) {
            try {
                // 두 번째 포맷으로 시도합니다 (yyyy-MM-dd HH:mm)
                LocalDateTime localDateTime = LocalDateTime.parse(this.paymentDate, formatter2);
                convertedPaymentDate = localDateTime.toLocalDate();
            } catch (Exception e2) {
                try {
                    // 세 번째 포맷으로 시도합니다 (yyyy년 M월 d일)
                    convertedPaymentDate = LocalDate.parse(this.paymentDate, formatter3);
                } catch (Exception e3) {
                    // 세 가지 형식이 모두 실패하면 예외를 던집니다.
                    throw new RuntimeException("지원되지 않는 날짜 형식입니다: " + this.paymentDate);
                }
            }
        }

        return Review.builder()
                .entId(this.entId)
                .userId(this.userId)
                .rating(this.rating)
                .reviewContent(this.reviewContent)
                .receipt(new Receipt(
                        this.bizNum.replace("-",""), // 사업자 번호에서 "-" 제거
                        this.entName, // 업체명
                        convertedPaymentDate, // 변환된 결제일자
                        Integer.parseInt(this.totalPrice.replace(",", "")) // 쉼표를 제거하고 정수로 변환한 총 결제금액
                        ))
                .build();
    }

    // Review엔티티 생성에 필요한 url에서 가져온 업체ID, Auth에서 가져온 userId를 넣어줌(set을 막아둬서 따로 메소드를 만들어 값 넣어줌)
    public void initializeIds(Long entId, Long userId) {
        this.entId = entId;
        this.userId = userId;
    }
}
