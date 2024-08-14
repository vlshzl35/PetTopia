package com.sh.pettopia.enterprise.repository;

import com.sh.pettopia.enterprise.entity.Enterprise;
import com.sh.pettopia.enterprise.entity.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalonRepository extends JpaRepository<Salon, Long> {
}
