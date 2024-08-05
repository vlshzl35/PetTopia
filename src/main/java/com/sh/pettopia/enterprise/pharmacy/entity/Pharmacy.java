package com.sh.pettopia.enterprise.pharmacy.entity;

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
@Table(name = "tbl_pharmacy")
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class Pharmacy extends Enterprise {

    public Pharmacy(int id, String name, String phone, String address, String officeHours, List<Review> reviews) {
        super(id, name, phone, address, officeHours, reviews);
    }
}
