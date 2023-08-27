package com.clinicaOdontologica.service.impl;

import com.clinicaOdontologica.repository.impl.OdontologoDaoH2;
import com.clinicaOdontologica.model.Odontologo;
import org.junit.jupiter.api.*;
import com.clinicaOdontologica.service.OdontologoService;

import java.util.ArrayList;

public class OdontologoServiceTest {
    private OdontologoService odontologoService= new OdontologoService(new OdontologoDaoH2());

    @BeforeEach
    public void cargar() {
        odontologoService.guardar(new Odontologo(1, "Francisco", "Rodriguez"));
        odontologoService.guardar(new Odontologo(2, "Pedro", "Fernandez"));
        odontologoService.guardar(new Odontologo(3, "Gonzalo", "Chaves"));
    }

    // @Test
    public void leerTodosTest() {
        ArrayList<Odontologo> listadoOdontologos = odontologoService.listarTodos();
        Assertions.assertEquals(3,listadoOdontologos.size());
    }

    // @Test
    public void buscarTest(){
        Odontologo odontologo = odontologoService.buscar(1).orElse(null);
        System.out.println(odontologo);
    }

    //@Test
    public void eliminarTest(){
        odontologoService.eliminar(3);
    }

}
