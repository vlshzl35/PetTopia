package com.sh.pettopia.enterprise.repository;

import com.sh.pettopia.enterprise.entity.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacyRepository extends JpaRepository<Enterprise, Long> {
}
