package com.sanvalero.gym.repository;

import com.sanvalero.gym.domain.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    //Clases que acceden Bases de datos
    List<Usuario> findAll();
    List<Usuario> findByLesionado(boolean lesionado);
    List<Usuario> findByDniUsuario(String dniUsuario);

}
