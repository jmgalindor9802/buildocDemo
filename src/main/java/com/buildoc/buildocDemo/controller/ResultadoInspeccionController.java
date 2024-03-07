package com.buildoc.buildocDemo.controller;

import com.buildoc.buildocDemo.services.imp.ResultadoInspeccionServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/persona/",method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.HEAD})
@CrossOrigin("*")
public class ResultadoInspeccionController {
    @Autowired
    private ResultadoInspeccionServiceImp resultadoInspeccionServiceImp;
}
