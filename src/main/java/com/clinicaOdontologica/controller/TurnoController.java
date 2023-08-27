package com.clinicaOdontologica.controller;

import com.clinicaOdontologica.model.Turno;
import com.clinicaOdontologica.repository.impl.OdontologoDaoH2;
import com.clinicaOdontologica.repository.impl.TurnoDaoH2;
import com.clinicaOdontologica.service.PacienteService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.clinicaOdontologica.repository.impl.DomicilioDaoH2;
import com.clinicaOdontologica.repository.impl.PacienteDaoH2;
import com.clinicaOdontologica.service.OdontologoService;
import com.clinicaOdontologica.service.TurnoService;

import java.util.ArrayList;

@RestController
@RequestMapping("/turnos")
public class TurnoController {


    private final TurnoService turnoService = new TurnoService(new TurnoDaoH2());
    private final PacienteService pacienteService = new PacienteService(new PacienteDaoH2(new DomicilioDaoH2()));
    private final OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());

    private static final Logger LOGGER =  Logger.getLogger(TurnoController.class);
    @PostMapping("/new")
    public ResponseEntity<Turno> guardar(@RequestBody Turno turno) {
        ResponseEntity<Turno> response;
        LOGGER.info("Guardando Turno");
        if (pacienteService.buscar(turno.getPaciente().getId()).isPresent() &&
                odontologoService.buscar(turno.getOdontologo().getId()).isPresent()) {
            LOGGER.info("Turno guardado con éxito.");
            response = ResponseEntity.ok(turnoService.guardar(turno));
        } else {
            LOGGER.error("Ocurrió un error al intentar guardar el Turno.");
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return response;
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<ArrayList<Turno>> listarTodos() {
        LOGGER.info("Listando todos los Tunros");
        return ResponseEntity.ok(turnoService.listarTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        ResponseEntity<String> response;
        LOGGER.info("Eliminando Turno.");
        if (turnoService.buscar(id).isPresent()) {
            turnoService.eliminar(id);
            LOGGER.info("Turno eliminado con éxito.");
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
        } else {
            LOGGER.error("Ocurrió un error al intentar eliminar el Turno.");
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @PutMapping("/update")
    public ResponseEntity<Turno> actualizar(@RequestBody Turno turno) {
        ResponseEntity<Turno> response = null;
        if (turno != null){
            LOGGER.info("Turno actualizado con éxito.");
            response = ResponseEntity.ok(turnoService.actualizar(turno));
        } else {
            LOGGER.error("Ocurrió un error al intentar actualizar el Turno.");
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscar(@PathVariable Integer id) {
        LOGGER.info("Buscando Turno.");
        Turno turno = turnoService.buscar(id).orElse(null);
        if(turno != null){
            LOGGER.info("Turno encontrado.");
            return ResponseEntity.ok(turno);
        } else {
            LOGGER.error("No se encontró el Turno.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
