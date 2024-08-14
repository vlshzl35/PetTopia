package com.sh.pettopia.choipetsitter.entity;

import lombok.Getter;

@Getter
public enum AvailableService {

    olderPetWalk("olderPetWalk")
    , youngerPetWalk("youngerPetWalk")
    , everydayWalk("everydayWalk")
    , medicine("medicine")
    , walkPickup("walkPickup")
    , play("play")
    , firstAid("firstAid")
    , longReservation("longReservation")
    , brush("brush");

    private final String petService;

    AvailableService(String petService){
        this.petService=petService;


    }
    public String getPetService(){
        return petService;
    }
}
