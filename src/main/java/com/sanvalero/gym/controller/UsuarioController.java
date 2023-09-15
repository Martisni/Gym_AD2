package com.sanvalero.gym.controller;

import com.sanvalero.gym.domain.Usuario;
import com.sanvalero.gym.exception.ErrorMessage;
import com.sanvalero.gym.exception.UsuarioNotFoundException;
import com.sanvalero.gym.service.UsuarioService;
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
public class UsuarioController {

    //Conexion con el Service
    @Autowired
    private UsuarioService usuarioService;
    //Conexion del logg para las trazas de control
    private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    //Metodos de listar, borrar, modificar y añadir
    @GetMapping("/gym/usuarios")
    public ResponseEntity<List<Usuario>> getUsuarios(@RequestParam(name = "lesionado", defaultValue = "") String lesionado) {
        if (lesionado.equals("")) {
            logger.debug("UsuarioController: Iniciado get usuario All");
            return ResponseEntity.ok(usuarioService.findAll());
        } else {
            boolean lesi = Boolean.parseBoolean(lesionado);
            logger.debug("UsuarioController: Iniciado el filtrado get");
            return ResponseEntity.ok(usuarioService.findByLesionado(lesi));
        }
    }

    @GetMapping("/gym/users/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable long id) throws UsuarioNotFoundException{
        Usuario usuarios = usuarioService.findById(id);
        logger.debug("UsuarioController: Iniciado get usuario");
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/gym/users")
    public ResponseEntity<List<Usuario>> getUsuario() {
        List<Usuario> usuarios = usuarioService.findAll();
        logger.debug("UsuarioController: Iniciado get usuario");
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping("/gym/usuario")
    public ResponseEntity<Usuario> addUsuario(@Valid @RequestBody Usuario usuario) throws UsuarioNotFoundException {
        Usuario deportista = usuarioService.addUsuario(usuario);
        logger.debug("UsuarioController: Iniciado post usuario");
        return ResponseEntity.status(HttpStatus.CREATED).body(deportista);
    }

    @DeleteMapping("/gym/usuario/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable long id) throws UsuarioNotFoundException {
        usuarioService.deleteUsuario(id);
        logger.debug("UsuarioController: Iniciado delete usuario");
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/gym/usuario/{id}")
    public ResponseEntity<Usuario> modifyUsuario(@PathVariable long id, @RequestBody Usuario usuario) throws UsuarioNotFoundException {
        Usuario modifiedUsuario = usuarioService.modifyUsuario(id, usuario);
        logger.debug("UsuarioController: Iniciado put usuario");
        return ResponseEntity.status(HttpStatus.OK).body(modifiedUsuario);
    }

    //Control de excepciones: 400, 404 y 500

    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleUsuarioNotFoundException(UsuarioNotFoundException unfe) {
        ErrorMessage errorMessage = new ErrorMessage(404, unfe.getMessage());
        logger.error("UsuarioController: Iniciado error 404 usuario");
        return  new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleBadRequestException(MethodArgumentNotValidException manve) {
        ErrorMessage badRequestErrorMessage = new ErrorMessage(400, "Bad Request");
        logger.error("UsuarioController: Iniciado error 400 usuario");
        return new ResponseEntity<>(badRequestErrorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception exception) {
        ErrorMessage errorMessage = new ErrorMessage(500, "Intenal Server Error");
        logger.error("UsuarioController: Iniciado error 500 usuario");
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
