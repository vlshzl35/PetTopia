package com.sh.pettopia.Hojji.pet.entity;

import lombok.Getter;

@Getter
// 예방접종 여부
public enum VaccinationType {
    RABIES("광견병"),        // 광견병
    COMBINED_VACCINE("종합 백신"), // 종합 백신
    CORONAVIRUS("코로나"),   // 코로나
    KENNEL_COUGH ("킨넬코프") ; // 켄넬코프

    private final String vaccinationTypeName;

    VaccinationType(String vaccinationTypeName)
    {
        this.vaccinationTypeName = vaccinationTypeName;
    }

     public String getVaccinationTypeName(){
        return vaccinationTypeName;
     }

}
