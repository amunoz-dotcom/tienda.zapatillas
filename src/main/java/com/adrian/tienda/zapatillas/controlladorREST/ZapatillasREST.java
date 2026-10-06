package com.adrian.tienda.zapatillas.controlladorREST;


import com.adrian.tienda.zapatillas.model.Zapatilla;
import com.adrian.tienda.zapatillas.servicios.ServicioZapatillas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("restZapatillas/")
public class ZapatillasREST {

    @Autowired
    private ServicioZapatillas servicioZapatillas;

    @RequestMapping("listar")
    public List<Zapatilla> listar(){
        return servicioZapatillas.obtenerZapatillas();
    }

    @RequestMapping("obtener-zapatillas")
    public Zapatilla obtenerZapatilla(@RequestParam("id") Long id){
        return servicioZapatillas.obtenerZapatillaPorid(id);
    }

}

