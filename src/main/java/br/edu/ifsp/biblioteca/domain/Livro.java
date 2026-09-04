package br.edu.ifsp.biblioteca.domain;

import java.util.ArrayList;
import java.util.List;

public class Livro {
    private Long id;
    private String isbn;
    private String titulo;
    private Integer anoPublicacao;
    private List<Exemplar>listaExemplares;
    private List<Autor> autores;

    public Livro(String isbn, String titulo, Integer anoPublicacao){
        this.isbn = isbn;
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
        this.listaExemplares = new ArrayList<>();
        this.autores = new ArrayList<>();
    }

    public Long getId() {return this.id;}

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn(){
        return isbn;
    }

    public void setIsbn(){
        this.isbn = isbn;
    }

    public String getTitulo(){
        return titulo;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public Integer getAnoPublicacao(){
        return anoPublicacao;
    }

    public void setAnoPublicacao(Integer anoPublicacao){
        this.anoPublicacao = anoPublicacao;
    }

    public void adcionarExemplar(Exemplar item){
        this.listaExemplares.add(item);
    }

    public void adicionarAutor(Autor autor){
        this.autores.add(autor);
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", titulo='" + titulo + '\'' +
                ", anoPublicacao=" + anoPublicacao +
                '}';
    }
}
