package com.sh.pettopia.parktj.petsitterfinder.repository;

import com.sh.pettopia.parktj.petsitterfinder.entity.CareRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetsitterCareRegistrationRepository extends JpaRepository<CareRegistration, Long> {
}
