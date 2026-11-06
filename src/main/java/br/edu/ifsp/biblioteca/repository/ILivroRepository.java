package br.edu.ifsp.biblioteca.repository;

import br.edu.ifsp.biblioteca.domain.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

//Define comportamentos que as clases que a implementam deve fazer exemplo salvar
//Interface sao os metodos que sao como um contrato que a classe deve aplicar se for dessa interface
//O contrato diz sobre o metodo, seus parametros e o que ele retorna

/*
* JpaRepository fornece os seguintes métodos
*  - save, findById, findAll, count...
*
*
* "Containing" vira LIKE "%...%", "IgnoreCase" vira LOWER(...).
*
* SELECT
*
* */

public interface ILivroRepository extends JpaRepository<Livro, Long> {
//    metodos com parametros(variáveis usadas localmente)


//    objeto container que pode ou nao conter um valor nulo, servindo para explicitar a ausencia e presença de um dado

    Optional<Livro> findByIsbn(String isbn);

    boolean existsByIsbn(String isbn);

    List<Livro> findByTituloContainingIgnoreCase(String trechoTitulo);

    Page<Livro> findByTituloContainingIgnoreCase(String trechoTitulo, Pageable pagina);

//    Deve retornar uma lista pois caso haja mais de um livro com titulo parecido ele retorna toda a lista
    List<Livro> buscarPorTitulo(String titulo);
}
