package com.sh.pettopia.parktj.petsitterfinder.repository;

import com.sh.pettopia.parktj.petsitterfinder.entity.CareRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareRegistrationRepository extends JpaRepository<CareRegistration, Long> {
}
