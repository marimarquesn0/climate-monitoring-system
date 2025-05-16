package com.example.climatemonitoring.service;

import com.example.climatemonitoring.data.UsuarioRepository;
import com.example.climatemonitoring.models.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    public Optional<Usuario> autenticar(String email, String senha) {
        return usuarioRepository.listarTodos()
                .stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst();
    }

    public boolean cadastrar(Usuario novoUsuario) {
        List<Usuario> usuarios = usuarioRepository.listarTodos();

        boolean jaExiste = usuarios.stream()
                .anyMatch(u -> u.getEmail().equalsIgnoreCase(novoUsuario.getEmail()));

        if (jaExiste) {
            return false;
        }

        usuarios.add(novoUsuario);
        usuarioRepository.salvarTodos(usuarios);
        return true;
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.listarTodos();
    }
}
