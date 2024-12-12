package com.financeiro.financeiro.controller;


import com.financeiro.financeiro.domain.despesas.Despesas;
import com.financeiro.financeiro.domain.receitas.DadosReceita;
import com.financeiro.financeiro.domain.receitas.Receitas;
import com.financeiro.financeiro.domain.receitas.ReceitasRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("receitas")
public class ReceitasController {

    @Autowired
    private ReceitasRepository receitasRepository;

    @PostMapping
    public ResponseEntity postReceita(@RequestBody @Valid DadosReceita dadosReceita) {

        // Consultar por despesas com a mesma descrição e o mesmo mês
        List<Receitas> despesasExistentes = receitasRepository.findByDescricaoAndMesAndAno(dadosReceita.descricao(), dadosReceita.mes(), dadosReceita.ano());

        // Se já existir uma despesa com a mesma descrição e mês, retorna um erro 409
        if (despesasExistentes != null && !despesasExistentes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Já existe uma receita com a mesma descrição neste mês.");
        }

        // Caso não haja conflito, criamos e salvamos a nova receita
        Receitas receitas = new Receitas(dadosReceita);
        receitasRepository.save(receitas);

        // Retorna HTTP 200 OK com a nova receita
        return ResponseEntity.ok(receitas);
    }

    @GetMapping
    public ResponseEntity<List<Receitas>> getAllReceitas() {

        List<Receitas> receitas = receitasRepository.findAll();

        return ResponseEntity.ok(receitas);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Receitas> getReceitasById(@PathVariable Long id) {
        if (receitasRepository.existsById(id)) {
            Receitas receitas = receitasRepository.findById(id).get();
            return ResponseEntity.ok(receitas);
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Receitas>> searchReceitas(@RequestParam(required = false) String titulo) {
        List<Receitas> receitas = receitasRepository.findByDescricao(titulo);
        if (receitas != null && !receitas.isEmpty()) {
            return ResponseEntity.ok(receitas);
        }else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/{ano}/{mes}")
    public ResponseEntity<List<Receitas>> searchReceitasByMes(@PathVariable(required = false) Integer ano, @PathVariable(required = false) Integer mes) {
        List<Receitas> receitas = receitasRepository.findByAnoAndMes(ano, mes);
        if (receitas != null && !receitas.isEmpty()) {
            return ResponseEntity.ok(receitas);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity putReceita(@PathVariable Long id, @RequestBody @Valid DadosReceita dadosReceita) {

        try {

            // Consultar por despesas com a mesma descrição e o mesmo mês
            List<Receitas> despesasExistentes = receitasRepository.findByDescricaoAndMesAndAno(dadosReceita.descricao(), dadosReceita.mes(), dadosReceita.ano());

            // Se já existir uma despesa com a mesma descrição e mês, retorna um erro 409
            if (despesasExistentes != null && !despesasExistentes.isEmpty()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Já existe uma receita com a mesma descrição neste mês.");
            }else {

                Receitas receitasAtual = receitasRepository.findById(id).get();

                receitasAtual.setDescricao(dadosReceita.descricao());
                receitasAtual.setMes(dadosReceita.mes());
                receitasAtual.setAno(dadosReceita.ano());
                receitasAtual.setValor(dadosReceita.valor());
                receitasRepository.save(receitasAtual);
                return ResponseEntity.ok(receitasAtual);

            }

        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteReceita(@PathVariable Long id) {
        if (receitasRepository.existsById(id)) {
            Receitas receitas = receitasRepository.findById(id).get();
            receitasRepository.delete(receitas);
            return ResponseEntity.ok(receitas);
        }else {
            return ResponseEntity.notFound().build();
        }
    }



}
