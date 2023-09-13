package com.sanvalero.gym.controller;

import com.sanvalero.gym.domain.Perfil;
import com.sanvalero.gym.exception.ErrorMessage;
import com.sanvalero.gym.exception.PerfilNotFoundException;
import com.sanvalero.gym.service.PerfilService;
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
public class PerfilController {

    //Conexion con el Service
    @Autowired
    private PerfilService perfilService;
    //Conexion del logg para las trazas de control
    private final Logger logger = LoggerFactory.getLogger(PerfilController.class);
    //Metodos de listar, borrar, modificar y añadir
    @GetMapping("/gym/perfiles")
    public ResponseEntity<List<Perfil>> getPerfiles(@RequestParam(name = "obesidad", defaultValue = "") String obesidad) {
        if (obesidad.equals("")) {
            return ResponseEntity.ok(perfilService.findAll());
        } else {
            boolean obe = Boolean.parseBoolean(obesidad);
            return ResponseEntity.ok(perfilService.findByObesidad(obe));
        }
    }

    @GetMapping("/gym/perfils")
    public ResponseEntity<List<Perfil>> getPerfil() {
        List<Perfil> perfiles = perfilService.findAll();
        logger.debug("PerfilController: Iniciado get perfil");
        return ResponseEntity.ok(perfiles);
    }

    @PostMapping("/gym/perfiles")
    public ResponseEntity<Perfil> addPerfil(@Valid @RequestBody Perfil perfil) throws PerfilNotFoundException {
        Perfil parametros = perfilService.addPerfil(perfil);
        logger.debug("HorarioController: Iniciado post perfil");
        return ResponseEntity.status(HttpStatus.CREATED).body(parametros);
    }

    @DeleteMapping("/gym/perfil/{id}")
    public ResponseEntity<Void> deletePerfil(@PathVariable long id) throws PerfilNotFoundException {
        perfilService.deletePerfil(id);
        logger.debug("HorarioController: Iniciado delete perfil");
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/gym/perfil/{id}")
    public  ResponseEntity<Perfil> modifyPerfil(@PathVariable long id, @RequestBody Perfil perfil) throws PerfilNotFoundException {
        Perfil modifiedPerfil = perfilService.modifyPerfil(id, perfil);
        logger.debug("HorarioController: Iniciado put perfil");
        return ResponseEntity.status(HttpStatus.OK).body(modifiedPerfil);
    }

    //Control de excepciones: 400, 404 y 500

    @ExceptionHandler(PerfilNotFoundException.class)
    public ResponseEntity<ErrorMessage> handlePerfilNotFoundException(PerfilNotFoundException pnfe) {
        ErrorMessage errorMessage = new ErrorMessage(404, pnfe.getMessage());
        logger.error("PerfilController: Iniciado error 404 perfil");
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleBadRequestException (MethodArgumentNotValidException manve) {
        ErrorMessage badRequestErrorMessage = new ErrorMessage(400, "Bad Request");
        logger.error("PerfilController: Iniciado error 400 perfil");
        return new ResponseEntity<>(badRequestErrorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception exception) {
        ErrorMessage errorMessage = new ErrorMessage(500,"Internal Server Error");
        logger.error("PerfilController: Iniciado error 500 perfil");
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
