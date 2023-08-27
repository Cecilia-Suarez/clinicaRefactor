package com.clinicaOdontologica.repository.impl;

import com.clinicaOdontologica.model.Turno;
import com.clinicaOdontologica.repository.IDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public class TurnoDaoH2 implements IDao<Turno> {
    private ArrayList<Turno> turnos;

    public TurnoDaoH2() {
        turnos = new ArrayList<>();
    }

    @Override
    public Turno guardar(Turno turno) {
        turnos.add(turno);
        return turno;
    }

    @Override
    public Optional<Turno> buscar(int id) {
        return turnos.stream().filter(turno -> turno.getId().equals(id)).findFirst();
    }

    @Override
    public void eliminar(int id) {
        turnos.removeIf(turno -> turno.getId().equals(id));
    }

    @Override
    public ArrayList<Turno> listarTodos() {
        return turnos;
    }

    @Override
    public Turno actualizar(Turno turno) {
        eliminar(turno.getId());
        turnos.add(turno);
        return turno;
    }
}
