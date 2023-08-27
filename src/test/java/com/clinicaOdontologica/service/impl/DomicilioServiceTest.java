package com.clinicaOdontologica.service.impl;

import com.clinicaOdontologica.model.Domicilio;
import com.clinicaOdontologica.repository.impl.DomicilioDaoH2;
import com.clinicaOdontologica.service.DomicilioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class DomicilioServiceTest {
    private DomicilioService domicilioService= new DomicilioService((new DomicilioDaoH2()));

    @BeforeEach
    public void cargar() {
        domicilioService.guardar(new Domicilio("Los Pinos", "1233", "Malvin", "Montevideo"));
        domicilioService.guardar(new Domicilio("Avda. Las Instrucciones del Año 1913", "1234", "Las Piedras", "Canelones"));
        domicilioService.guardar(new Domicilio("Colonia", "1303", "Centro", "Montevideo"));
    }

    //@Test
    public void leerTodosTest() {
        ArrayList<Domicilio> listadoDomicilios = domicilioService.listarTodos();
        Assertions.assertEquals(3, listadoDomicilios.size());
    }

    //@Test
    public void buscarTest(){
        Domicilio domicilio = domicilioService.buscar(1).orElse(null);
        System.out.println(domicilio);
    }

    @Test
    public void eliminarTest(){
        domicilioService.eliminar(3);
    }

}

