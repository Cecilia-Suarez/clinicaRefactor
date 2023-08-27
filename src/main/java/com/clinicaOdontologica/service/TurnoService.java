package com.clinicaOdontologica.service;

import com.clinicaOdontologica.model.Turno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clinicaOdontologica.repository.IDao;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class TurnoService {

    private IDao<Turno> turnoIDao;

    @Autowired
    public TurnoService(IDao<Turno> turnoIDao) {
        this.turnoIDao = turnoIDao;
    }

    public Turno guardar(Turno turno) {
        return turnoIDao.guardar(turno);
    }

    public ArrayList<Turno> listarTodos() {
        return turnoIDao.listarTodos();
    }

    public void eliminar(Integer id) {
        turnoIDao.eliminar(id);
    }

    public Turno actualizar(Turno turno) {
        return turnoIDao.actualizar(turno);
    }

    public Optional<Turno> buscar(Integer id) {
        return turnoIDao.buscar(id);
    }

}
