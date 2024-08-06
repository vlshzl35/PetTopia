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
@Table(name = "tbl_salon")
@Data
@NoArgsConstructor
@ToString
public class Salon extends Enterprise {

    public Salon(int entId, String entName, String entPhone, String entAddress, String OfficeHours, List<Review> entReviews) {
        super(entId, entName, entPhone, entAddress, OfficeHours, entReviews);
    }
}


