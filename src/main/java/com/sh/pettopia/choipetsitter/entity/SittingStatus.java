package com.sh.pettopia.choipetsitter.entity;

import lombok.Getter;

@Getter
public enum SittingStatus {

    StandbySitting("돌봄대기"), SITTING_STATUS("돌봄중"),COMPLETED_SITTING ("돌봄완료");

    private final String sittingStatus;
    SittingStatus(String sittingStatus){
        this.sittingStatus=sittingStatus;
    }
}
