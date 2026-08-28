package br.edu.ifsp.biblioteca;

import br.edu.ifsp.biblioteca.domain.Livro;
import br.edu.ifsp.biblioteca.domain.Usuario;

// Toda classe java herda da classe OBJECT que ṕossui o metodo to string por exemplo por isso e possivel utiliza-lo sendo uma herança para todas as classes
public class BibliotecaApplication {
    public static void main(String[] args) {
        Livro livro1 = new Livro(
//            1L,
            "4512368558452",
            "A Luta Do Glúten",
            2026);

        Livro livro2 = new Livro(
//                2L,
                "978-8581053127",
                "Dois Mundos, Um Herói",
                2015
                );

        Livro livro3 = new Livro(
//                3L,
                "1445465421453",
                "O Homem Por Trás Da Luva",
                2026
        );

        System.out.println(livro1);
        System.out.println(livro2);
        System.out.println(livro3);
    }
}
