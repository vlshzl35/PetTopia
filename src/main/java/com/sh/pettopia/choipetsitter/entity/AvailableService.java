package com.sh.pettopia.choipetsitter.entity;

import lombok.Getter;

@Getter
public enum AvailableService {

    노견산책("olderPetWalk")
    , 어린견산책("youngerPetWalk")
    , 매일산책("everydayWalk")
    , 약먹이기("medicine")
    , 도보픽업("walkPickup")
    , 실내놀이("play")
    , 응급처치("firstAid")
    , 장기예약("longReservation")
    , 모발관리("brush");

    private final String petService;

    AvailableService(String petService){
        this.petService=petService;
    }

}
