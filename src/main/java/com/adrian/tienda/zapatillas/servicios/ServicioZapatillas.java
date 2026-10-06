package com.adrian.tienda.zapatillas.servicios;

import com.adrian.tienda.zapatillas.model.Zapatilla;

import java.util.List;

public interface ServicioZapatillas {

    void registrarZapatilla(Zapatilla v);

    List<Zapatilla> obtenerZapatillas();

    Zapatilla obtenerZapatillaPorid(long id);

    void borrarZapatilla(long id);

    void guardarCambiosZapatilla(Zapatilla zapatillaEditar) throws Exception;


}
