package com.sh.pettopia.enterprise.common.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.sql.Timestamp;

@Embeddable // receipt_id를 알고있다
@Data
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Receipt {
    private Timestamp date;
    private int totalPrice;
}
