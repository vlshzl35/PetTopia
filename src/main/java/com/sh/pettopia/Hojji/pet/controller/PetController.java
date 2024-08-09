package com.sh.pettopia.Hojji.pet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pet")
public class PetController {
    @GetMapping("/registPet")
    public void registPet() {

    }
}
