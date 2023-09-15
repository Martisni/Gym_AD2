package com.sanvalero.gym.controller;

import com.sanvalero.gym.domain.Rutina;
import com.sanvalero.gym.exception.ErrorMessage;
import com.sanvalero.gym.exception.RutinaNotFoundException;
import com.sanvalero.gym.service.RutinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class RutinaController {


    //Conexion con el Service
    @Autowired
    private RutinaService rutinaService;
    //Conexion del logg para las trazas de control
    private final Logger logger = LoggerFactory.getLogger(RutinaController.class);
    //Metodos de listar, borrar, modificar y añadir
    @GetMapping("/gym/rutinas")
    public ResponseEntity<List<Rutina>> getRutinas(@RequestParam(name = "material", defaultValue = "") String material) {
        if (material.equals("")) {
            return ResponseEntity.ok(rutinaService.findAll());
        } else {
            boolean mate = Boolean.parseBoolean(material);
            return ResponseEntity.ok(rutinaService.findByMaterial(mate));
        }
    }

    @GetMapping("/gym/rutins/{id}")
    public ResponseEntity<Rutina> getRutinaById(@PathVariable long id) throws RutinaNotFoundException {
        Rutina rutinas = rutinaService.findById(id);
        logger.debug("RutinaController: Iniciado get rutinas");
        return ResponseEntity.ok(rutinas);
    }

    @GetMapping("/gym/rutins")
    public ResponseEntity<List<Rutina>> getRutina() {
        List<Rutina> rutinas = rutinaService.findAll();
        logger.debug("RutinaController: Iniciado get rutinas");
        return ResponseEntity.ok(rutinas);
    }

    @PostMapping("/gym/rutinas")
    public ResponseEntity<Rutina> addRutina(@Valid @RequestBody Rutina rutina) throws RutinaNotFoundException {
        Rutina tablas = rutinaService.addRutina(rutina);
        logger.debug("RutinaController: Iniciado post rutinas");
        return ResponseEntity.status(HttpStatus.CREATED).body(tablas);
    }

    @DeleteMapping("/gym/rutina/{id}")
    public ResponseEntity<Void> deleteRutina(@PathVariable long id) throws RutinaNotFoundException {
        rutinaService.deleteRutina(id);
        logger.debug("RutinaController: Iniciado delete rutinas");
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/gym/rutina/{id}")
    public ResponseEntity<Rutina> modifyRutina(@PathVariable long id, @RequestBody Rutina rutina) throws RutinaNotFoundException {
        Rutina modifiedRutina = rutinaService.modifyRutina(id, rutina);
        logger.debug("RutinaController: Iniciado put rutinas");
        return ResponseEntity.status(HttpStatus.OK).body(modifiedRutina);
    }

    //Control de excepciones: 400, 404 y 500

    @ExceptionHandler(RutinaNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleRutinaNotFoundException(RutinaNotFoundException rnfe) {
        ErrorMessage errorMessage = new ErrorMessage(404, rnfe.getMessage());
        logger.error("RutinaController: Iniciado error 404 rutina");
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleBadRequestException(MethodArgumentNotValidException manve) {
        ErrorMessage badRequestErrorMessage = new ErrorMessage(400, "Bad Request");
        logger.error("RutinaController: Iniciado error 400 rutina");
        return new ResponseEntity<>(badRequestErrorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception exception) {
        ErrorMessage errorMessage = new ErrorMessage(500, "Internal Server Error");
        logger.error("RutinaController: Iniciado error 500 rutina");
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
