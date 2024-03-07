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

    @GetMapping("/listarPersonas") // Cambia la ruta aquí
    public String listarPersonas(Model model) {
        model.addAttribute("personas", personaServices.listarPersonas());
        return "personas";
    }
}