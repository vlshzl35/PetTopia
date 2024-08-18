package com.sh.pettopia.parktj.petsitterfinder.repository;

import com.sh.pettopia.parktj.petsitterfinder.entity.CareRegistration;
import com.sh.pettopia.parktj.petsitterfinder.entity.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
        Page <CommentEntity> findAllByCareRegistrationOrderByIdDesc(CareRegistration careRegistration, Pageable pageable);
}
