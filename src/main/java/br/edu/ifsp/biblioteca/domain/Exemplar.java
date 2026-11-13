package br.edu.ifsp.biblioteca.domain;

import org.hibernate.annotations.Columns;
import org.hibernate.annotations.ManyToAny;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Exemplas")
public class Exemplar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String codigo;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EStatusExemplar status;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "livro_id", nullable = false)
    private  Livro livro;

    protected Exemplar() {

    }

    
    public Exemplar(String codigo, Livro livro) {
        this.codigo = codigo;
        this.livro = livro;
        this.status = EStatusExemplar.DISPONIVEL;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public EStatusExemplar getStatus() {
        return status;
    }

    public void setStatus(EStatusExemplar status) {
        this.status = status;
    }

    public void emprestar() {
        if (this.status == EStatusExemplar.EMPRESTADO) {
            throw new IllegalStateException("Exemplar " + this.codigo + "já está emprestado!");
        }

        this.status = EStatusExemplar.EMPRESTADO;
    }
    public void devolver() {
        this.status = EStatusExemplar.DISPONIVEL;
    }

    public boolean estaDisponivel() {
        return this.status == EStatusExemplar.DISPONIVEL;
    }

    @Override
    public String toString() {
        return "Exemplar{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", livro=" + livro +
                ", status=" + status +
                '}';
    }
}
