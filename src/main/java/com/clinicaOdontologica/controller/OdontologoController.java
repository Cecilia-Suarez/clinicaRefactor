package com.clinicaOdontologica.controller;

import com.clinicaOdontologica.model.Odontologo;
import com.clinicaOdontologica.repository.impl.OdontologoDaoH2;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.clinicaOdontologica.service.OdontologoService;

import java.util.ArrayList;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());
    private static final Logger LOGGER =  Logger.getLogger(OdontologoController.class);

    @PostMapping("/new")
    public ResponseEntity<Odontologo> guardar(@RequestBody Odontologo odontologo) {
        LOGGER.info("Guardando Odontólogo con id: " + odontologo.getId());
        LOGGER.info("Odontólogo guardado con éxito.");
        return ResponseEntity.ok(odontologoService.guardar(odontologo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscar(@PathVariable Integer id) {
        LOGGER.info("Buscando Odontólogo.");
        Odontologo odontologo = odontologoService.buscar(id).orElse(null);
        if (odontologo == null){
            LOGGER.error("No se encontró al Odontólogo.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        LOGGER.info("Odontólogo encontrado.");
        return ResponseEntity.ok(odontologo);
    }

    @PutMapping("/update")
    public ResponseEntity<Odontologo> actualizar(@RequestBody Odontologo odontologo) {
        LOGGER.info("Actualizando Odontólogo con id: " + odontologo.getId());
        ResponseEntity<Odontologo> response = null;
        if (odontologo.getId() != null && odontologoService.buscar(odontologo.getId()).isPresent()) {
            LOGGER.info("Odontólogo actualizado con éxito.");
            System.out.println(odontologo);
            response = ResponseEntity.ok(odontologoService.actualizar(odontologo));
        } else {
            LOGGER.error("Ocurrió un error al intentar actualizar al Odontólogo.");
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<ArrayList<Odontologo>> listarTodos() {
        LOGGER.info("Listando todos los Odontólogos.");
        return ResponseEntity.ok(odontologoService.listarTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        ResponseEntity<String> response = null;
        LOGGER.info("Eliminando al Odontólogo.");
        if (odontologoService.buscar(id).isPresent()) {
            odontologoService.eliminar(id);
            LOGGER.info("Odontólogo eliminado con éxito.");
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
        } else {
            LOGGER.error("Ocurrió un error al intentar eliminar al Odontólogo.");
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }
}
