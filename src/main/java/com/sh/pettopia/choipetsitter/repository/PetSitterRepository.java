package com.sh.pettopia.choipetsitter.repository;

import com.sh.pettopia.choipetsitter.entity.AvailablePetSize;
import com.sh.pettopia.choipetsitter.entity.AvailableService;
import com.sh.pettopia.choipetsitter.entity.PetSitter;
import com.sh.pettopia.choipetsitter.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PetSitterRepository extends JpaRepository<PetSitter,String> {

    PetSitter findPetSitterByPetSitterId(String petSitterId);

    @Query("select p from petSitter p where p.petSitterAddress.address like %:address%")
    List<PetSitter> findByPetSitterAddressContaining(@Param("address") String address);
}
