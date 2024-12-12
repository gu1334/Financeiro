package com.financeiro.financeiro.domain.despesas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosDespesas(
        @NotBlank(message = "não pode ser vazio") String descricao,
        @NotNull(message = "não pode ser vazio") double valor,
        @NotNull(message = "não pode ser vazio") int mes,
        @NotNull(message = "O ano não pode ser vzio") int ano,
        String categoria // Categoria agora é String (para aceitar "" ou null)
) {

    // Construtor adicional para atribuir categoria padrão se vazia
    public DadosDespesas {
        if (categoria == null || categoria.trim().isEmpty()) {
            categoria = Categoria.OUTROS.name(); // Define Categoria.OUTROS caso a categoria esteja vazia ou null
        }
    }

    // Método para converter a categoria recebida para o enum
    public Categoria categoriaEnum() {
        try {
            return Categoria.valueOf(categoria); // Converte para o valor do enum correspondente
        } catch (IllegalArgumentException e) {
            return Categoria.OUTROS; // Se a categoria não for válida, retorna Categoria.OUTROS
        }
    }
}
