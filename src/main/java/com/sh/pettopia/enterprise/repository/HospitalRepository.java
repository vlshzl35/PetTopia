package com.sh.pettopia.enterprise.repository;

import com.sh.pettopia.enterprise.dto.EnterpriseDetailResponseDto;

import com.sh.pettopia.enterprise.entity.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<Enterprise, Long> {

}
