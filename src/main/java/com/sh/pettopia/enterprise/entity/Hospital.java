package com.sh.pettopia.enterprise.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tbl_hospital")
@Data
@NoArgsConstructor
@ToString
public class Hospital extends Enterprise {
    // 고유 pk없음


    public Hospital(Long entId, String entName, String entPhone, String entAddress, String officeHours) {
        super(entId, entName, entPhone, entAddress, officeHours);
    }
}
