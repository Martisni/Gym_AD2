package com.sanvalero.gym.service;

import com.sanvalero.gym.domain.Usuario;
import com.sanvalero.gym.exception.UsuarioNotFoundException;

import java.util.List;

public interface UsuarioService {
    //Listar distintos tipos de campos en usuario
    List<Usuario> findAll();
    List<Usuario> findByLesionado(boolean lesionado);
    List<Usuario> findByDniUsuario(String dniUsuario);
    Usuario findById(long id) throws UsuarioNotFoundException;

    //Crear usuario
    Usuario addUsuario(Usuario usuario) throws UsuarioNotFoundException;

    //Borrar usuario
    void deleteUsuario(long id) throws UsuarioNotFoundException;

    //Modificar usuario
    Usuario modifyUsuario(long id, Usuario newUsuario) throws UsuarioNotFoundException;
}
