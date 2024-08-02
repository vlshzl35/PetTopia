package com.sh.pettopia.enterprise.pharmacy.entity;

import com.sh.pettopia.enterprise.entity.Enterprise;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tbl_pharmacy")
@Data
@NoArgsConstructor
@ToString
public class Pharmacy extends Enterprise {

    public Pharmacy(int id, String name, String phone, String address, String officeHours) {
        super(id, name, phone, address, officeHours);
    }
}
