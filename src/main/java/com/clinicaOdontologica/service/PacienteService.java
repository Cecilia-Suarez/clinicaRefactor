package com.clinicaOdontologica.service;

import com.clinicaOdontologica.model.Paciente;
import com.clinicaOdontologica.repository.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PacienteService {
    private IDao<Paciente> pacienteIDao;

    @Autowired
    public PacienteService(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }
    public Paciente guardar(Paciente paciente){
        Paciente pacienteGuardado = pacienteIDao.guardar(paciente);
        return pacienteGuardado;
    }
    public Optional<Paciente> buscar(int id){
        return pacienteIDao.buscar(id);
    }
    public ArrayList<Paciente> listarTodos(){
        return pacienteIDao.listarTodos();
    }
    public void eliminar(int id){
        pacienteIDao.eliminar(id);
    }

    public Paciente actualizar(Paciente p) {
        return pacienteIDao.actualizar(p);
    }
}
