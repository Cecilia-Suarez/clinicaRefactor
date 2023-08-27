package com.clinicaOdontologica.service;

import com.clinicaOdontologica.model.Odontologo;
import com.clinicaOdontologica.repository.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class OdontologoService {

    private static IDao<Odontologo> idaoOdontologo;

    @Autowired
    public OdontologoService(IDao<Odontologo> idaoOdontologo) {
        this.idaoOdontologo = idaoOdontologo;
    }

    public Odontologo guardar(Odontologo odontologo){
        Odontologo odontologoGuardado = idaoOdontologo.guardar(odontologo);
        return odontologoGuardado;
    }

    public ArrayList<Odontologo> listarTodos(){
        return idaoOdontologo.listarTodos();
    }

    public Optional<Odontologo> buscar(int id){
        return idaoOdontologo.buscar(id);
    }

    public void eliminar (int id){
        idaoOdontologo.eliminar(id);
    }

    public Odontologo actualizar(Odontologo odontologo) {
        return idaoOdontologo.actualizar(odontologo);
    }
}
