package com.sh.pettopia.enterprise.salon.entity;

import com.sh.pettopia.enterprise.entity.Enterprise;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "tbl_salon")
@Data
@NoArgsConstructor
@ToString
public class Salon extends Enterprise {

    public Salon(int id, String name, String phone, String address, String officeHours) {
        super(id, name, phone, address, officeHours);
    }
}


