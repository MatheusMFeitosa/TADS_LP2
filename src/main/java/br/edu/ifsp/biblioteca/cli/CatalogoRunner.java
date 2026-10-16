package br.edu.ifsp.biblioteca.cli;

import br.edu.ifsp.biblioteca.domain.Livro;
import br.edu.ifsp.biblioteca.domain.Usuario;
import br.edu.ifsp.biblioteca.exception.RegraDeNegocioException;
import br.edu.ifsp.biblioteca.repository.LivroRepositoryEmMemoria;
import br.edu.ifsp.biblioteca.repository.UsuarioRepositoryEmMemoria;
import br.edu.ifsp.biblioteca.service.LivroService;
import br.edu.ifsp.biblioteca.service.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

//CLI (Comand Line Interface) como nao temos interface é aqui que testaremos nosso codigo
//@Component o que faz:
@Component
public class CatalogoRunner implements CommandLineRunner {
    private final LivroService livroService;
    private final UsuarioService usuarioService;

    public CatalogoRunner(LivroService livroService, UsuarioService usuarioService ){
        this.livroService = livroService;
        this.usuarioService = usuarioService;
    }


    public void run(String... args){
        System.out.println();
        System.out.println("=== Biblioteca IFSP = v0 (tudo em memória) ===");

        Livro domCasmurro = new Livro("9788568145607", "Dom Casmurro", 1899);

        this.livroService.cadastrar(domCasmurro);

        this.livroService.adicionarAutor(domCasmurro.getId(), "Machado de Assis");
        this.livroService.adicionarExemplar(domCasmurro.getId(), "DC-001");
        this.livroService.adicionarExemplar(domCasmurro.getId(), "DC-002");

        Livro homemTrasLuva = new Livro("9788508145603", "Vidas Secas", 1938);

        this.livroService.cadastrar(homemTrasLuva);

        this.livroService.adicionarAutor(domCasmurro.getId(), "Graciliano ramos");
        this.livroService.adicionarExemplar(domCasmurro.getId(), "VC-001");

        this.usuarioService.cadastrar(new Usuario("Ana Souza", "ana@ifsp.edu.br"));
        this.usuarioService.cadastrar(new Usuario("Bruno Lima", "bruno@ifsp.edu.br"));

        System.out.println();
        System.out.println("=== Catálogo ===");

        for(Livro livro : this.livroService.listarTodos()){
            System.out.println(" " + livro);
        }

        System.out.println();
        System.out.println("=== Usuários ===");

        for(Usuario usuario : this.usuarioService.listarTodos()){
            System.out.println(" " + usuario);
        }

        System.out.println();
        System.out.println("=== Buscar por título contendo 'casmurro' ===");

        List<Livro> encontrados = this.livroService.buscarPorTitulo("casmurro");

        for (Livro livro : encontrados){
            System.out.println(" " + livro);
        }

        System.out.println();
        System.out.println("=== Testando cadastrar o mesmo ISBN ===");

        try{
            this.livroService.cadastrar(
                    new Livro("9788508145603", "Vidas Secas", 1938)
            );
        }catch (RegraDeNegocioException erro){
            System.out.println(" Regra de negócio impediu: " + erro.getMessage());
        }
    }
}
