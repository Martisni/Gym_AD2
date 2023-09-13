package com.sanvalero.gym.service;

import com.sanvalero.gym.domain.Rutina;
import com.sanvalero.gym.exception.RutinaNotFoundException;

import java.util.List;

public interface RutinaService {

    //Listar distintos tipos de campos en rutina
    List<Rutina> findAll();
    List<Rutina> findByMaterial(boolean material);
    List<Rutina> findByModalidad(String modalidad);
    Rutina findById(long id) throws RutinaNotFoundException;
    //Crear rutina
    Rutina addRutina(Rutina rutina) throws RutinaNotFoundException;
    //Borrar rutina
    void deleteRutina (long id) throws RutinaNotFoundException;
    //Modificar rutina
    Rutina modifyRutina(long id, Rutina newRutina) throws RutinaNotFoundException;

}
