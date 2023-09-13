package com.sanvalero.gym.service;

import com.sanvalero.gym.domain.Perfil;
import com.sanvalero.gym.exception.PerfilNotFoundException;
import com.sanvalero.gym.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfilServiceImpl implements PerfilService{

    @Autowired
    private PerfilRepository perfilRepository;

    @Override
    public List<Perfil> findAll() {
        return perfilRepository.findAll();
    }

    @Override
    public Perfil findById(long id) throws PerfilNotFoundException {
        return perfilRepository.findById(id)
                .orElseThrow(PerfilNotFoundException::new);
    }

    @Override
    public List<Perfil> findByObesidad(boolean obesidad) {
        return perfilRepository.findByObesidad(obesidad);
    }

    @Override
    public Perfil addPerfil(Perfil perfil) throws PerfilNotFoundException {
        return perfilRepository.save(perfil);
    }

    @Override
    public void deletePerfil(long id) throws PerfilNotFoundException {
        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(PerfilNotFoundException::new);
        perfilRepository.delete(perfil);
    }

    @Override
    public Perfil modifyPerfil(long id, Perfil newPerfil) throws PerfilNotFoundException {
        Perfil existingPerfil = perfilRepository.findById(id)
                .orElseThrow(PerfilNotFoundException::new);
        existingPerfil.setFechaMedicion(newPerfil.getFechaMedicion());
        existingPerfil.setObesidad(newPerfil.isObesidad());
        existingPerfil.setRitmoCardico(newPerfil.getRitmoCardico());
        existingPerfil.setImc(newPerfil.getImc());
        existingPerfil.setPeso(newPerfil.getPeso());
        existingPerfil.setMedidas(newPerfil.getMedidas());
        return perfilRepository.save(existingPerfil);
    }


}
