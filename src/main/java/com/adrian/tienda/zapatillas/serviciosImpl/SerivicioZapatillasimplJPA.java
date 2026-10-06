package com.adrian.tienda.zapatillas.serviciosImpl;

import com.adrian.tienda.zapatillas.model.Zapatilla;
import com.adrian.tienda.zapatillas.servicios.ServicioZapatillas;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class SerivicioZapatillasimplJPA implements ServicioZapatillas {



    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void registrarZapatilla(Zapatilla v) {

        try {
            v.setImagenPortada(v.getFotoSubida().getBytes());
        } catch (IOException e) {
            System.out.println("NO PUDE PROCESAR LA FOTO SUBIDA");
            throw new RuntimeException(e);
        }
        entityManager.persist(v);

    }

    @Override
    public List<Zapatilla> obtenerZapatillas() {
        return entityManager.createQuery("select v from Zapatilla v").getResultList();
    }




    @Override
    public Zapatilla obtenerZapatillaPorid(long id) {
        return entityManager.find(Zapatilla.class, id);
    }

    @Override
    public void borrarZapatilla(long id) {
        // En JPA la forma mas comun de borrar un registro,
        // Es pedirlo, comprobar que existe y luego borrarlo
        Zapatilla zapatillaAborrar = entityManager.find(Zapatilla.class, id);
        if (zapatillaAborrar != null) {
            entityManager.remove(zapatillaAborrar);
        } else {
            System.out.println("id no encontrada");
        }
    }

    @Override
    public void guardarCambiosZapatilla(Zapatilla zapatillaEditar) throws Exception {
        if(zapatillaEditar.getFotoSubida().getSize() == 0){
            Zapatilla original =
                    entityManager.find(Zapatilla.class, zapatillaEditar.getId());
            zapatillaEditar.setImagenPortada(original.getImagenPortada());
        } else{
            zapatillaEditar.setImagenPortada(
                    zapatillaEditar.getFotoSubida().getBytes()
            );
        }
        // merge actualiza el registro si ya existe
        // si no existe lo crea
        entityManager.merge(zapatillaEditar);
    }


}
