package com.sh.petsitter;

public enum AvailableService {

    oldpetwalk("노견산책")
    , youngerpetwalk("어린견산책")
    , everydaywalk("매일산책")
    , medicine("약먹이기")
    , walkpickup("도보픽업")
    , play("실내놀이")
    , firstaid("응급처치")
    , longreservation("장기예약")
    , brush("모발관리");

    private final String petService;

    AvailableService(String petService){
        this.petService=petService;
    }

    public String getPetService(){
        return petService;
    }
}
