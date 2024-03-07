package com.buildoc.buildocDemo.controller;


import com.buildoc.buildocDemo.services.PersonaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonaController {
    @Autowired
    private PersonaServices personaServices;

    @GetMapping({"/persona","/"})
    public String listarEquipo(Model modelo){
        modelo.addAttribute("personas", personaServices.listarPersonas());
        return "personas";
    }
}