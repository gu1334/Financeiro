package com.financeiro.financeiro.novoUsuario;

import jakarta.validation.constraints.NotBlank;

public record DadosNovoUsuario(

        @NotBlank(message = "Email não pode ser vazio") String email,
        @NotBlank(message = "senha não pode ser vazio") String senha

) {}
