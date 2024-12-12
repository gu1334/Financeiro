package com.financeiro.financeiro.controller;

import com.financeiro.financeiro.novoUsuario.NovoUsuario;
import com.financeiro.financeiro.novoUsuario.NovoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/NovoUsuario")
public class NovoUsuarioController {

    @Autowired
    NovoUsuarioRepository novoUsuarioRepository;

    @PostMapping
    public ResponseEntity<NovoUsuario> novoUsuario(@RequestBody NovoUsuario novoUsuario) {
        novoUsuarioRepository.save(novoUsuario);
        return ResponseEntity.ok(novoUsuario);
    }

}
