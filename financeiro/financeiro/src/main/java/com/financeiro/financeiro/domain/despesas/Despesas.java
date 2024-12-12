package com.financeiro.financeiro.domain.despesas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

//nome da tabela
@Table(name = "despesas")

@Entity(name = "Despesas")
@Getter
@NoArgsConstructor // Construtor padrão (sem parâmetros)
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Despesas {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private double valor;
    private int mes;
    private int ano;

    private String categoria;

    public Despesas(DadosDespesas dadosDespesas) {
        this.descricao = dadosDespesas.descricao();
        this.valor = dadosDespesas.valor();
        this.mes = dadosDespesas.mes();
        this.categoria = dadosDespesas.categoria();
        this.ano = dadosDespesas.ano();

    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
