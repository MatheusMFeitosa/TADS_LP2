package br.edu.ifsp.biblioteca.repository;

import br.edu.ifsp.biblioteca.domain.Livro;

import java.util.List;
import java.util.Optional;

//Define comportamentos que as clases que a implementam deve fazer exemplo salvar
//Interface sao os metodos que sao como um contrato que a classe deve aplicar se for dessa interface
//O contrato diz sobre o metodo, seus parametros e o que ele retorna

public interface ILivroRepository {
//    metodos com parametros(variáveis usadas localmente)
    Livro salvar(Livro livro);

    List<Livro> listarTodos();

//    objeto container que pode ou nao conter um valor nulo, servindo para explicitar a ausencia e presença de um dado
    Optional<Livro> buscarPorId(Long id);

    Optional<Livro> buscarPorIsbn(String isbn);

//    Deve retornar uma lista pois caso haja mais de um livro com titulo parecido ele retorna toda a lista
    List<Livro> buscarPorTitulo(String titulo);
}
