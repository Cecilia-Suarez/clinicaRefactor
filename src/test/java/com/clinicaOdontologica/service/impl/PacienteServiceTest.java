package com.clinicaOdontologica.service.impl;

import com.clinicaOdontologica.repository.impl.DomicilioDaoH2;
import com.clinicaOdontologica.repository.impl.PacienteDaoH2;
import com.clinicaOdontologica.model.Domicilio;
import com.clinicaOdontologica.model.Paciente;
import com.clinicaOdontologica.service.PacienteService;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Date;

public class PacienteServiceTest {
    private PacienteService pacienteService= new PacienteService(new PacienteDaoH2(new DomicilioDaoH2()));

    @BeforeEach
    public void cargar() {
        pacienteService.guardar(new Paciente("Rafael", "Umpierrez", "123456", new Date(2023-01-01), new Domicilio("Los Pinos", "1234", "Malvin", "Montevideo")));
        pacienteService.guardar(new Paciente("Agustín", "Peréz", "123456", new Date(2023-01-01), new Domicilio("Los Pinos", "1234", "Malvin", "Montevideo")));
        pacienteService.guardar(new Paciente("Rodrigo", "Fernández", "123456", new Date(2023-01-01), new Domicilio("Los Pinos", "1234", "Malvin", "Montevideo")));
    }

    //@Test
    public void leerTodosTest() {
        ArrayList<Paciente> listadoPacientes = pacienteService.listarTodos();
        Assertions.assertEquals(3, listadoPacientes.size());
    }

    //@Test
    public void buscarTest(){
        Paciente paciente = pacienteService.buscar(1);
        System.out.println(paciente);
    }

    //@Test
    public void eliminarTest(){
        pacienteService.eliminar(3);
    }

}
