package com.sh.pettopia.choipetsitter.repository;

import com.sh.pettopia.choipetsitter.entity.Sitting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SittingRepository extends JpaRepository<Sitting,Long> {

    Sitting findByPartnerOrderId(String partnerOrderId);

    List<Sitting> findAllByOrderByServiceDateAsc();

    List<Sitting> findByMemberId(String memberId);

    @Query("SELECT s FROM sitting s WHERE s.memberId = :memberId AND s.sittingStatus IN ('MEMBER_CHECK', 'WAITING_MEMBER_CHECK')")
    List<Sitting> findAllBySittingStatusIsCompleteOrWating(@Param("memberId") String memberId);

    @Query("SELECT s FROM sitting s WHERE s.memberId = :memberId AND s.sittingStatus IN ('StandbySitting', 'START_SITTING')")
    List<Sitting> findAllBySittingStatusIsReadyOrStart(@Param("memberId") String memberId);

}
