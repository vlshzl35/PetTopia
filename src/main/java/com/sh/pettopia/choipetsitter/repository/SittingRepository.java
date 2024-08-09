package com.sh.pettopia.choipetsitter.repository;

import com.sh.pettopia.choipetsitter.entity.Sitting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SittingRepository extends JpaRepository<Sitting,Long> {
}
