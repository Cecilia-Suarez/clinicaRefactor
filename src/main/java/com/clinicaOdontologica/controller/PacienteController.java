package com.clinicaOdontologica.controller;

import com.clinicaOdontologica.model.Paciente;
import com.clinicaOdontologica.service.PacienteService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.clinicaOdontologica.repository.impl.DomicilioDaoH2;
import com.clinicaOdontologica.repository.impl.PacienteDaoH2;

import java.util.ArrayList;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService = new PacienteService(new PacienteDaoH2(new DomicilioDaoH2()));
    private static final Logger LOGGER =  Logger.getLogger(PacienteController.class);

    @PostMapping("/new")
    public ResponseEntity<Paciente> guardar(@RequestBody Paciente paciente){
        LOGGER.info("Guardando Paciente con id: " + paciente.getId());
        LOGGER.info("Paciente guardado con éxito.");
        return ResponseEntity.ok(pacienteService.guardar(paciente));

    }

    @PutMapping("/update")
    public ResponseEntity<Paciente> actualizar(@RequestBody Paciente paciente) {
        LOGGER.info("Actualizando Paciente con id: " + paciente.getId());
        ResponseEntity<Paciente> response = null;
        if (paciente.getId() != null && pacienteService.buscar(paciente.getId()).isPresent()) {
            LOGGER.info("Paciente actualizado con éxito.");
            response = ResponseEntity.ok(pacienteService.actualizar(paciente));
        } else {
            LOGGER.error("Ocurrió un error al intentar actualizar al Paciente.");
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscar(@PathVariable Integer id) {
        LOGGER.info("Buscando Paciente.");
        Paciente paciente = pacienteService.buscar(id).orElse(null);
        if (paciente == null){
            LOGGER.error("No se encontró al Paciente.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        LOGGER.info("Paciente encontrado.");
        return ResponseEntity.ok(paciente);

    }

    @GetMapping("/listarTodos")
    public ResponseEntity<ArrayList<Paciente>> listarTodos() {
        LOGGER.info("Listando todos los Pacientes.");
        return ResponseEntity.ok(pacienteService.listarTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        ResponseEntity<String> response = null;
        LOGGER.info("Eliminando al Paciente.");
        if (pacienteService.buscar(id).isPresent()) {
            pacienteService.eliminar(id);
            LOGGER.info("Paciente eliminado con éxito.");
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
        } else {
            LOGGER.error("Ocurrió un error al intentar eliminar al Paciente.");
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

}

