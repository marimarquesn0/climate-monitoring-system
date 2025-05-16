package com.example.climatemonitoring.data;

import com.example.climatemonitoring.models.Usuario;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UsuarioRepository {

    private final Persistence<Usuario> persistence;

    public UsuarioRepository() {
        this.persistence = new JsonPersistence<>("dados/usuarios.json", Usuario.class);
    }

    public List<Usuario> listarTodos() {
        return persistence.listarTodos();
    }

    public void salvarTodos(List<Usuario> usuarios) {
        persistence.salvarTodos(usuarios);
    }
}

