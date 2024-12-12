package com.financeiro.financeiro.domain.receitas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.YearMonth;

public record DadosReceita(

        @NotBlank(message = "descrição não pode ser vazio") String descricao,
        @NotNull(message = "o valor não pode ser vazio") double valor,

        @NotNull(message = "Obrigatorio o mês") int mes,
        @NotNull(message = "Obrigatorio o ano") int ano




) {}
