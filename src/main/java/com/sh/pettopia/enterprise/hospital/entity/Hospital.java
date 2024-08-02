package com.sh.pettopia.enterprise.hospital.entity;

import com.sh.pettopia.enterprise.entity.Enterprise;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table(name = "tbl_hospital")
@Data
@NoArgsConstructor
@ToString
public class Hospital extends Enterprise {
    // 고유 pk없음

    public Hospital(int id, String name, String phone, String address, String officeHours) {
        super(id, name, phone, address, officeHours);
    }
}
