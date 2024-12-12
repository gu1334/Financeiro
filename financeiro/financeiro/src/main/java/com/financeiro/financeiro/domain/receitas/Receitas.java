package com.financeiro.financeiro.domain.receitas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

//nome da tabela
@Table(name = "receitas")
//nome da classe
@Entity(name = "Receitas")
@Getter
@NoArgsConstructor // Construtor padrão (sem parâmetros)
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Receitas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private double valor;


    private int mes;
    private int ano;


    public Receitas(DadosReceita dadosReceita) {

        this.descricao = dadosReceita.descricao();
        this.valor = dadosReceita.valor();
        this.mes = dadosReceita.mes();
        this.ano = dadosReceita.ano();

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

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
}
