package com.adrian.tienda.zapatillas.controlladorImagen;


import com.adrian.tienda.zapatillas.servicios.ServicioZapatillas;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class ControlladorImagen {

    @Autowired
    private ServicioZapatillas servicioZapatillas;

    @RequestMapping("mostrar_imagen")
    public void mostrarImagen(@RequestParam("id") Long id, HttpServletResponse response) throws IOException {

        byte[] info=
                servicioZapatillas.obtenerZapatillaPorid(id).getImagenPortada();
        if (info == null ){
            return;
        }
        //emitir info
        response.setContentType
                ("image/jpeg, image/jpg, image/png, image/gif, image/webp");
        response.getOutputStream().write(info);
        response.getOutputStream().close();


    }


}
