package br.edu.ifsp.biblioteca.service;

import br.edu.ifsp.biblioteca.domain.Autor;
import br.edu.ifsp.biblioteca.domain.Exemplar;
import br.edu.ifsp.biblioteca.domain.Livro;
import br.edu.ifsp.biblioteca.exception.RegraDeNegocioException;
import br.edu.ifsp.biblioteca.repository.ILivroRepository;

import java.util.List;
import java.util.Optional;

// Service para estabelecer regras de negócio (Como validaçoes) que depende do repository uma vez que e la que fazemos
// os objetos
public class LivroService {

// Ao instanciarmos a interface do repository podemos alterar na interface mas o service continua atualizado pois os
// repositorys sao obrigados a implementar esses metodos
    private  final ILivroRepository livroRepository;

// Construtror par para colocar um valor pois a viraval e final
    public LivroService(ILivroRepository repository){
        this.livroRepository = repository;
    }

    public Livro cadastrar(Livro livro){
        if (livro.getTitulo() == null || livro.getTitulo().isEmpty()){
            throw new RegraDeNegocioException("O Título é obrigatoório!");
        }

        if (livro.getIsbn() == null || livro.getIsbn().isEmpty()){
            throw new RegraDeNegocioException("O ISBN é obrigatoório!");
        }

        if (livro.getAnoPublicacao() <= 0){
            throw new RegraDeNegocioException("O ano de publicação é obrigatoório!");
        }

        Optional<Livro> livroJaCadastradoOptional = this.livroRepository.buscarPorIsbn(livro.getIsbn());

        if (livroJaCadastradoOptional.isPresent()){
            throw new RegraDeNegocioException("Já existe um Livro cadastrado com o ISBN: " + livro.getIsbn());
        }

        Livro livroCadastrado = this.livroRepository.salvar(livro);

        return livroCadastrado;
    }

    public Livro adicionarExemplar(Long livroId, String codigoDoExemplar){
        Livro livro = this.buscarPorId(livroId);
        livro.adcionarExemplar(new Exemplar(codigoDoExemplar, livro));

        return this.livroRepository.salvar(livro);
    }

    public Livro adicionarAutor(Long livroId, String nomeAutor){
        Livro livroEncontrado = this.buscarPorId(livroId);
        livroEncontrado.adicionarAutor(new Autor(nomeAutor));

        return this.livroRepository.salvar(livroEncontrado);
    }

    public Livro buscarPorId(Long id){
        Optional<Livro> livroOptional = this.livroRepository.buscarPorId(id);

        if (livroOptional.isPresent()){
            return livroOptional.get();
        }

        throw new RegraDeNegocioException("Livro não encontrado: " + id);
    }

    public List<Livro> buscarPorTitulo(String trechoDoTitulo){
        return this.livroRepository.buscarPorTitulo(trechoDoTitulo);
    }

    public List<Livro> listarTodos(){
        return this.livroRepository.listarTodos();
    }
}
