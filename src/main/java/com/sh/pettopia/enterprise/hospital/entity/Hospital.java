package com.sh.pettopia.enterprise.hospital.entity;

import com.sh.pettopia.enterprise.common.entity.Enterprise;
import com.sh.pettopia.enterprise.common.entity.Review;
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


    public Hospital(int id, String name, String phone, String address, String officeHours, List<Review> reviews) {
        super(id, name, phone, address, officeHours, reviews);
    }
}
