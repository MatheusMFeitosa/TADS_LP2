package br.edu.ifsp.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// Toda classe java herda da classe OBJECT que ṕossui o metodo to string por exemplo por isso e possivel utiliza-lo sendo uma herança para todas as classes
@SpringBootApplication //
public class BibliotecaApplication {
    public static void main(String[] args) {
        SpringApplication.run(BibliotecaApplication.class, args);
    }
}

//Diferença de sobrecarga e sobreescrita de metodo: Sobrecarga quando tem varios metodos com o mesmo nome mas com parametros diferentes
// (podendo ter mais ou menos parametros), sobreescrita e quando um uma classe pai tem um metodo e uma classe filha herda esse metodo e
// utiliza esse metodo para o seu próprio fim.

