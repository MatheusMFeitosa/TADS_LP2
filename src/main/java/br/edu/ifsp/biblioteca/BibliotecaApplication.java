package br.edu.ifsp.biblioteca;

import br.edu.ifsp.biblioteca.domain.Livro;
import br.edu.ifsp.biblioteca.domain.Usuario;
import br.edu.ifsp.biblioteca.repository.ILivroRepository;
import br.edu.ifsp.biblioteca.repository.UsuarioRepositoryEmMemoria;
import br.edu.ifsp.biblioteca.repository.LivroRepositoryEmMemoria;

import java.util.List;
import java.util.Optional;


// Toda classe java herda da classe OBJECT que ṕossui o metodo to string por exemplo por isso e possivel utiliza-lo sendo uma herança para todas as classes
public class BibliotecaApplication {
    public static void main(String[] args) {
        Livro livro = new Livro("4512368558452", "A Luta Do Glúten", 2026);

        Livro livro1 = new Livro("978-8581053127", "Dois Mundos, Um Herói", 2015);

        Livro livro2 = new Livro("1445465421453", "O Homem Por Trás Da Luva", 2026);

        System.out.println(livro);
        System.out.println(livro1);
        System.out.println(livro2);

        ILivroRepository livroRepository = new LivroRepositoryEmMemoria();

        livroRepository.salvar(livro);
        livroRepository.salvar(livro1);
        livroRepository.salvar(livro2);

        Optional<Livro> livro1ptional = livroRepository.buscarPorId((1L));
        Optional<Livro> livro2Optional = livroRepository.buscarPorId(10L);

        if (livro1ptional.isPresent()){
            Livro l1 = livro1ptional.get();
            System.out.println("Livro com ID 1:" + l1);
        }

        if (livro2Optional.isPresent()){
            Livro l2 = livro2Optional.get();
            System.out.println("Livro com ID 10" + l2);
        }else {
            System.out.println("Livro com ID nao encontrado");
        }

        List<Livro> encontrados = livroRepository.buscarPorTitulo("A Luta");

        System.out.println(encontrados);
    }
}
