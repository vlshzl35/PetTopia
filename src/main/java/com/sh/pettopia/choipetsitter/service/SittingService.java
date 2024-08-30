package com.sh.pettopia.choipetsitter.service;

import com.sh.pettopia.choipetsitter.entity.Sitting;
import com.sh.pettopia.choipetsitter.repository.SittingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SittingService {

    private final SittingRepository sittingRepository;

    public void save(Sitting sitting)
    {
        sittingRepository.save(sitting);
    }

    public Sitting findByPartnerOrderId(String findByPartnerOrderId)
    {
        return sittingRepository.findByPartnerOrderId(findByPartnerOrderId);
    }

    public List<Sitting> findAllByOrderByServiceDateAsc() {
        return sittingRepository.findAllByOrderByServiceDateAsc();
    }

    public List<Sitting> findByMemberId(String memberId) {
        return sittingRepository.findByMemberId(memberId);
    }

    public List<Sitting> findAllBySittingStatusIsMemberCheck(String memberId)
    {
        return sittingRepository.findAllBySittingStatusIsMemberCheck(memberId);
    }

    public List<Sitting> findAllBySittingStatusIsReadyOrStartOrWaitingMemberCheck(String memberId)
    {
        return sittingRepository.findAllBySittingStatusIsReadyOrStartOrWaitingMemberCheck(memberId);
    }
}
