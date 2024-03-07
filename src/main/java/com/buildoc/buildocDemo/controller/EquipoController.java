package com.buildoc.buildocDemo.controller;

import com.buildoc.buildocDemo.entities.Equipo;
import com.buildoc.buildocDemo.services.EquipoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EquipoController {
    @Autowired
    private EquipoServices equipoServices;
    @GetMapping("/listarEquipos")
    public String listarEquipos(Model model) {
        model.addAttribute("equipos", equipoServices.listarEquipos());
        return "equipos";
    }
}