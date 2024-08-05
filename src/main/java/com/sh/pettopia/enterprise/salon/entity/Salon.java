package com.sh.pettopia.enterprise.salon.entity;


import com.sh.pettopia.enterprise.common.entity.Enterprise;
import com.sh.pettopia.enterprise.common.entity.Review;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tbl_salon")
@Data
@NoArgsConstructor
@ToString
public class Salon extends Enterprise {

    public Salon(int id, String name, String phone, String address, String officeHours, List<Review> reviews) {
        super(id, name, phone, address, officeHours, reviews);
    }
}


