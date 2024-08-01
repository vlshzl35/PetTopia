package com.sh.pettopia.enterprise.salon.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Popol {
    private String popolName; // 포폴명
    private String popolImage; // 사진경로
    private String popolExplain; // 설명
}
