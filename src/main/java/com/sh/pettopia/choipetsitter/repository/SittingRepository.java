package com.sh.pettopia.choipetsitter.repository;

import com.sh.pettopia.choipetsitter.entity.Sitting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SittingRepository extends JpaRepository<Sitting,Long> {

    Sitting findByPartnerOrderId(String partnerOrderId);

    List<Sitting> findAllByOrderByServiceDateAsc();
}
