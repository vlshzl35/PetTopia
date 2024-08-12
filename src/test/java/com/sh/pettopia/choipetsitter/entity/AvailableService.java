package com.sh.pettopia.choipetsitter.entity;

import lombok.Getter;

@Getter
public enum AvailableService {

    olderPetWalk("olderPetWalk")
    , youngerPetWalk("youngerPetWalk")
    , everydayWalk("매일산책")
    , medicine("medicine")
    , walkPickup("도보픽업")
    , play("실내놀이")
    , firstAid("응급처리")
    , longReservation("장기예약")
    , brush("빗질");

    private final String petService;

    AvailableService(String petService){
        this.petService=petService;
    }

}
