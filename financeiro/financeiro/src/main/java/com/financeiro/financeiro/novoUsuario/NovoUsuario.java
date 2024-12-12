package com.financeiro.financeiro.novoUsuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Table(name = "usuarios")

@Entity(name = "NovoUsuario")
@Getter
@NoArgsConstructor // Construtor padrão (sem parâmetros)
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class NovoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String senha;

    public NovoUsuario(DadosNovoUsuario dadosNovoUsuario) {
        this.email = dadosNovoUsuario.email();
        this.senha = dadosNovoUsuario.senha();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
