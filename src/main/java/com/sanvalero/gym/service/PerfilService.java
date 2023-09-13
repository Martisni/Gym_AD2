package com.sanvalero.gym.service;

import com.sanvalero.gym.domain.Perfil;
import com.sanvalero.gym.exception.PerfilNotFoundException;

import java.util.List;

public interface PerfilService {
    //Listar distintos tipos de campos en perfil
    List<Perfil> findAll();
    Perfil findById(long id) throws PerfilNotFoundException;
    List<Perfil> findByObesidad(boolean obesidad);
    //Crear perfil
    Perfil addPerfil(Perfil perfil) throws PerfilNotFoundException;
    //Borrar perfil
    void deletePerfil(long id) throws PerfilNotFoundException;
    //Modificar perfil
    Perfil modifyPerfil(long id, Perfil newPerfil) throws PerfilNotFoundException;
}
