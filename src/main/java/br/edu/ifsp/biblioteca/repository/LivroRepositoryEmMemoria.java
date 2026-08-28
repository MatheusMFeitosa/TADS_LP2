package br.edu.ifsp.biblioteca.repository;

import br.edu.ifsp.biblioteca.domain.Livro;

import java.util.*;

public class LivroRepositoryEmMemoria implements ILivroRepository{

//Map é uma interface do proprio Java, que define dois tipo <K, V>(generics - aceita qualquer tipo) (K = Key, V = Value) (ID, Livro)
//Em linguagens como Java define um atributo que é visível apenas dentro da própria classe (private) e cujo valor não pode ser alterado após a inicialização (final).
//HashMap<>() é uma classe que implementa o método Map
    private final Map<Long, Livro> livros = new HashMap<Long, Livro>(); //Container que guarda os livros
    private Long sequenciaId = 0L;


    @Override
    public Livro salvar(Livro livro) {

        if(livro.getId() == null){
            //Pós incremento ou seja apos a verificação se o id existe ele adiciona um a sequenciaId
            livro.setId(this.sequenciaId++);
        }
        //Put metodo que insere uma entrada em um HashMap<>
        this.livros.put(livro.getId(), livro);
        return livro;
    }

    @Override
    public List<Livro> listarTodos() {
        List<Livro> listaLivros = new ArrayList<>();

        for (Livro meulivro : livros.values()){
            listaLivros.add(meulivro);
        }

        return listaLivros;
    }

//Static é um metodo que esta ligada a classe não ao objeto, nao podendo ser acessado pelo objeto e sim pelo nome da classe
    @Override
    public Optional<Livro> buscarPorId(Long id) {  // Optional e uma extrutura de dados que diz que algo é opcional

        return Optional.ofNullable(this.livros.get(id));

    // Usando operador ternário
    //        return livro == null ? Optinal.empty() : optional.of(livro);
    //        Livro livro = this.livros.get(id);
    //
    // Usando if's
    //        if (livro == null){
    //            //É um container que pode estar vazil
    //            return Optional.empty();
    //        }
    }

    @Override
    public Optional<Livro> buscarPorIsbn(String isbn) {
        // Pegar todos os livros dentro do HashMap - metodo que retorna toda a lista para isso o values() e colection é para armazenalos
        List<Livro> colecaoLivros = new ArrayList<>(this.livros.values());

        for (int i = 0; i < colecaoLivros.size(); i++) {
            Livro livro = colecaoLivros.get(i);
            //equalsIgnoreCase serve para nao fazer distincao entre maiusculo e minusculo
            if (livro.getIsbn().equalsIgnoreCase(isbn)) {
                return Optional.of(livro);
            }
        }

        //Melhor forma de uso seria o forEach pois é uma lista
        return Optional.empty();
    }

    @Override
    public List<Livro> buscarPorTitulo(String titulo) {
        List<Livro> colecaoLivros = new ArrayList<>(this.livros.values());
        List<Livro> livrosEncontrados = new ArrayList<>();

        //Tipo do objeto que eu quero buscar na lista, Lista que quero procurar esse objeto
        for (Livro meulivro : colecaoLivros){
            //contains para se por exemplo o usuario colocar so o comeco do titulo ele retorna-ra todo com esse começo
            if (meulivro.getTitulo().contains(titulo)){
                livrosEncontrados.add(meulivro);
            }
        }
        return livrosEncontrados;
    }
}
