package br.edu.ifsp.biblioteca.domain;

// Classe define um modelo de dados - sendo uma forma e o objeto sendo o bolo que e criado nesse molde
public class Autor {
    private Long id;
    private String nome;

    //Construtor é método que faz a inicialização das propriedades da classe podendo criar varios construtores
    public Autor(String nome){
        //this se refere ao nome do atributo da propria classe
        this.nome = nome;
    }

    //get server para acessar um atributo publico e set para alterar algo nao retornando nada

    public Long getId(){
        return this.id;
    }

    public void setId(){
        this.id = id;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(){
        this.nome = nome;
    }

    // Para formatar o systemOut sobreescrevendo-o com o Override
    @Override
    public String toString(){
        return this.nome;
    }
}