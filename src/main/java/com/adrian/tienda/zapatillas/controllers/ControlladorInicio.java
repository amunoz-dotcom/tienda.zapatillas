package com.adrian.tienda.zapatillas.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControlladorInicio {

    @RequestMapping()
    public String inicio(){
        return "index";
    }
}
