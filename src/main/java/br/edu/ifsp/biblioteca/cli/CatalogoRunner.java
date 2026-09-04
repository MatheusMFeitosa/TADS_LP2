package br.edu.ifsp.biblioteca.cli;

import br.edu.ifsp.biblioteca.domain.Usuario;
import br.edu.ifsp.biblioteca.repository.LivroRepositoryEmMemoria;
import br.edu.ifsp.biblioteca.repository.UsuarioRepositoryEmMemoria;
import br.edu.ifsp.biblioteca.service.LivroService;
import br.edu.ifsp.biblioteca.service.UsuarioService;

//CLI (Comand Line Interface) como nao temos interface é aqui que testaremos nosso codigo
public class CatalogoRunner {
    private final LivroService livroService;
    private final UsuarioService usuarioService;

    public CatalogoRunner(){
        this.livroService = new LivroService(new LivroRepositoryEmMemoria());
        this.usuarioService = new UsuarioService(new UsuarioRepositoryEmMemoria());
    }
    
}
