package com.sanvalero.gym.service;

import com.sanvalero.gym.domain.Usuario;
import com.sanvalero.gym.exception.UsuarioNotFoundException;
import com.sanvalero.gym.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public List<Usuario> findByLesionado(boolean lesionado) {
        return usuarioRepository.findByLesionado(lesionado);
    }

    @Override
    public List<Usuario> findByDniUsuario(String dniUsuario) {
        return usuarioRepository.findByDniUsuario(dniUsuario);
    }

    @Override
    public Usuario findById(long id) throws UsuarioNotFoundException {
        return usuarioRepository.findById(id)
                .orElseThrow(UsuarioNotFoundException::new);
    }

    @Override
    public Usuario addUsuario(Usuario usuario) throws UsuarioNotFoundException {
        return usuarioRepository.save(usuario);
    }

    @Override
    public void deleteUsuario(long id) throws UsuarioNotFoundException {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(UsuarioNotFoundException::new);
        usuarioRepository.delete(usuario);
    }

    @Override
    public Usuario modifyUsuario(long id, Usuario newUsuario) throws UsuarioNotFoundException {
        Usuario existingUsuario = usuarioRepository.findById(id)
                .orElseThrow(UsuarioNotFoundException::new);
        existingUsuario.setNombreUsuario(newUsuario.getNombreUsuario());
        existingUsuario.setCorreo(newUsuario.getCorreo());
        existingUsuario.setPhone(newUsuario.getPhone());
        existingUsuario.setLesionado(newUsuario.isLesionado());
        return usuarioRepository.save(existingUsuario);
    }
}
