package com.clinicaOdontologica.service;

import com.clinicaOdontologica.model.Domicilio;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clinicaOdontologica.repository.IDao;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class DomicilioService {
    private static final Logger LOGGER =  Logger.getLogger(DomicilioService.class);
    private IDao<Domicilio> iDaoDomicilio;
    @Autowired
    public DomicilioService(IDao<Domicilio> iDaoDomicilio) {
        this.iDaoDomicilio = iDaoDomicilio;
    }

    public Domicilio guardar(Domicilio domicilio){
        Domicilio domicilioGuardado = iDaoDomicilio.guardar(domicilio);
        LOGGER.info("Guardando Domicilio: "+ domicilioGuardado.toString());
        return domicilioGuardado;
    }
    public Optional<Domicilio> buscar(int id){
        LOGGER.info("Buscando Domicilio");
        return iDaoDomicilio.buscar(id);
    }
    public ArrayList<Domicilio> listarTodos(){
        LOGGER.info("Listando todos los Domicilios");
        return iDaoDomicilio.listarTodos();
    }
    public void eliminar(int id){
        iDaoDomicilio.eliminar(id);
        LOGGER.info("Eliminando al Domicilio");
    }

    public Domicilio actualizar(Domicilio domicilio) {
        LOGGER.info("Actualizando Domicilio");
        return iDaoDomicilio.actualizar(domicilio);
    }
}
