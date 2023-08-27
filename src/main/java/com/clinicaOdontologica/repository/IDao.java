package com.clinicaOdontologica.repository;

import java.util.ArrayList;
import java.util.Optional;

public interface IDao<T> {

    public T guardar(T t);
    public Optional<T> buscar(int id);
    public void eliminar(int id);
    public ArrayList<T> listarTodos();
    T actualizar(T t);

}
