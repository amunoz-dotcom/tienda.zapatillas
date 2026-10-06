package com.adrian.tienda.zapatillas.controllers;


import com.adrian.tienda.zapatillas.model.Zapatilla;
import com.adrian.tienda.zapatillas.servicios.ServicioZapatillas;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("admin/")
public class ControlladorZapatillas {


    @Autowired
    private ServicioZapatillas servicioZapatillas;

    @RequestMapping("gestionarZapatillas")
    public String gestionarZapatillas(Model model){
        model.addAttribute( "zapatillas" , servicioZapatillas.obtenerZapatillas());
        return "admin/gestionar_productos";
    }

    @RequestMapping("registrarZapatilla")
    public String registrarZapatilla(Model model){
        Zapatilla nuevo = new Zapatilla();
        nuevo.setPrecio(1);
        model.addAttribute("nuevo", nuevo);
        return "admin/registro_producto";
    }



    @RequestMapping("guardarZapatilla")
    public String guardarZapatilla(
            @ModelAttribute("nuevo")
            @Valid
            Zapatilla nuevaZapatilla,
            BindingResult resultadoValidacion,
            Model model ){
        System.out.println("recibido para registrar: " + nuevaZapatilla);
        if( resultadoValidacion.hasErrors()){
            // si el videojuego tiene errores, mandamos al usuario a
            // el formulario de nuevo
            return "admin/registro_producto";
        }
        servicioZapatillas.registrarZapatilla(nuevaZapatilla);
        return "admin/registro_producto_ok";
    }

    @RequestMapping("borrarZapatilla")
    public String borrarZapatilla(@RequestParam("id") Long id, Model model){
        //repaso: model es una variable donde meto lo que quiera que le llegue
        //a la vista indicada en el return
        System.out.println("id recibida para borrar registro: " + id);
        servicioZapatillas.borrarZapatilla(id);
        return gestionarZapatillas(model);
    }


    @RequestMapping("editarZapatilla")
    public String editarZapatilla(@RequestParam("id") Long id, Model model){
        model.addAttribute("zapatillaEditar",
                servicioZapatillas.obtenerZapatillaPorid(id));
        return "admin/editar_producto";
    }

    //el metodo va a atender la ruta guardarCambiosVideojuego
    // a esa ruta se llega al hacer click en el GUARDAR CAMBIOS del form de edicion
    @RequestMapping("guardarCambiosZapatilla")
    public String guardarCambiosZapatilla(
            @ModelAttribute("zapatillaEditar") //th:object del form
            @Valid //para validar con las reglas puestas con @ en Videjuego.java
            Zapatilla zapatillaEditar, //objeto que contiene lo introducido en el form
            BindingResult resultadoValidacion,
            Model model ) throws Exception { //model en estos metodos, es para meterle lo que queramos que le llegue a la vista devuelta en el return
        if( resultadoValidacion.hasErrors()){
            return "admin/editar_producto";
        }else{
            servicioZapatillas.guardarCambiosZapatilla(zapatillaEditar);
            return gestionarZapatillas(model);
        }
    }



}
