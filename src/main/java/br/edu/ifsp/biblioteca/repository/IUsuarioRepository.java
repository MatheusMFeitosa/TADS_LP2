package br.edu.ifsp.biblioteca.repository;

import br.edu.ifsp.biblioteca.domain.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioRepository {
    Usuario salvar(Usuario usuario);

    Optional<Usuario> buscarPorId(Long id);

    Optional<Usuario> bucarPorEmail(String email);

    List<Usuario> listarTodos();
}
