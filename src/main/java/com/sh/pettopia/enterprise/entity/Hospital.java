package com.sh.pettopia.enterprise.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tbl_hospital")
@Data
@NoArgsConstructor
@ToString
public class Hospital extends Enterprise {
    // 고유 pk없음

    public Hospital(Long entId, String entName, String bizNum, String entPhone, String entAddress, String officeHours, String entUrl, String introduction) {
        super(entId, entName, bizNum, entPhone, entAddress, officeHours, entUrl, introduction);
    }
}
