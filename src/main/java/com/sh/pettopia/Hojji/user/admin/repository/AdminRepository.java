package com.sh.pettopia.Hojji.user.admin.repository;

import com.sh.pettopia.Hojji.user.admin.entity.PetsitterQualificationApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<PetsitterQualificationApplicationEntity, Long> {
}
