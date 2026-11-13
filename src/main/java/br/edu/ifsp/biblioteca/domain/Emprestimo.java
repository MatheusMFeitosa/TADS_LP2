package br.edu.ifsp.biblioteca.domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "emprestimo")
public class Emprestimo {
    public static final int PRAZO_EM_DIAS = 14;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "exemplar_id")
    private Exemplar exemplar;

    @Column(name = "data_emprestimo", nullable = false)
    private LocalDate dataEmprestimo;

    @Column(name = "data_devolucao")
    private LocalDate dataDevolucao;

    @Column(name = "data_prevista_devolucao")
    private LocalDate dataPrevistaDevolucao;

    protected Emprestimo() {}

    public Emprestimo(Usuario usuario, Exemplar exemplar, LocalDate dataEmprestimo) {
        this.usuario = usuario;
        this.exemplar = exemplar;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevistaDevolucao = dataEmprestimo.plusDays(PRAZO_EM_DIAS);
    }

    public boolean isAtivo(){
        return this.dataDevolucao == null;
    }

    public boolean IsAtrasado(LocalDate hoje) {
        return this.isAtivo() && this.dataPrevistaDevolucao.isBefore(hoje);
    }

    public long diasDeAtraso(LocalDate hoje) {
        if (!this.IsAtrasado(hoje)) {
            return 0;
        }

        return ChronoUnit.DAYS.between(this.dataPrevistaDevolucao, hoje);
    }

    public void registrarDevolucao(LocalDate quando) {
        if (this.isAtivo()) {
            throw new IllegalStateException("Este empréstim já foi devolvido");
        }

        this.dataDevolucao = quando;
        this.exemplar.devolver();
    }

    

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    @Override
    public String toString() {
        return "Emprestimo #" + this.id
                + "| previsto para" + this.dataPrevistaDevolucao
                + (this.isAtivo() ? " | EM ABERTO: " : " | devolvido em: ") + this.dataDevolucao;
    }
}
