package com.sh.pettopia.parktj.petsitterfinder.repository;

import com.sh.pettopia.choipetsitter.entity.PetSitter;
import com.sh.pettopia.parktj.petsitterfinder.dto.ReservationResponseDto;
import com.sh.pettopia.parktj.petsitterfinder.entity.ReservationByPetSitter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationByPetSitterRepository extends JpaRepository<ReservationByPetSitter, Long> {




    boolean existsByPetSitter_PetSitterIdAndPostId(String currentPetSitterId, Long currentPostId);

    List<ReservationByPetSitter> findReservationByPostId(Long postId);


    List<ReservationByPetSitter> findByPetSitter(PetSitter petSitter);

    List<ReservationByPetSitter> findReservationByMemberId(Long id);
}
