package br.edu.ifsp.biblioteca.repository;

import br.edu.ifsp.biblioteca.domain.EStatusExemplar;
import br.edu.ifsp.biblioteca.domain.Exemplar;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IExemplarRepository extends JpaRepository<Exemplar, Long> {
    Optional<Exemplar> findByCodigo(String codigo);

    List<Exemplar> findByIdAndStatus(Long livroId, EStatusExemplar status);

    long countByLivroIdAndStatus(Long livroId, EStatusExemplar status);

}
