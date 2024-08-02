package com.sh.pettopia.parktj.petsitterfinder.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/petsitterfinder")
@Slf4j
public class PetSitterFinderController {

    @GetMapping("/petsitterfinderdetail")
    public void petsitterFinderDetail(){

    }

    @GetMapping("petsitterfinderlist")
    public void petsitterFinderList(){

    }

    @GetMapping("/petsitterfinderfrm")
    public void petsitterFinderFrm(){

    }
}
