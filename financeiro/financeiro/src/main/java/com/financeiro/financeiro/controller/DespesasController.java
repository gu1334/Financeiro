package com.financeiro.financeiro.controller;

import com.financeiro.financeiro.domain.despesas.Categoria;
import com.financeiro.financeiro.domain.despesas.DadosDespesas;
import com.financeiro.financeiro.domain.despesas.Despesas;
import com.financeiro.financeiro.domain.despesas.DespesasRepository;
import com.financeiro.financeiro.domain.receitas.Receitas;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("despesas")
public class DespesasController {

    @Autowired
    private DespesasRepository despesasRepository;

    @PostMapping
    public ResponseEntity salvar(@RequestBody DadosDespesas dadosDespesas) {

        try {
            List<Despesas> despesasExistentes = despesasRepository.findByDescricaoAndMesAndAno(dadosDespesas.descricao(), dadosDespesas.mes(), dadosDespesas.ano());

            if (despesasExistentes != null && !despesasExistentes.isEmpty()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Já existe uma receita com a mesma descrição neste mês.");
            }
            Despesas despesas = new Despesas(dadosDespesas);
            despesasRepository.save(despesas);
            return ResponseEntity.status(HttpStatus.CREATED).body(despesas);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping
    public List<Despesas> getall() {
        List<Despesas> despesas = despesasRepository.findAll();
        return despesas;
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable Long id) {
        Optional<Despesas> despesas = despesasRepository.findById(id);
        if (despesas.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(despesas.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Despesas>> search(@RequestParam(required = false) String titulo) {
        List<Despesas> despesas = despesasRepository.findByDescricao(titulo);
        if (despesas != null && !despesas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(despesas);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{ano}/{mes}")
    public ResponseEntity<List<Despesas>> searchReceitasByMes(@PathVariable(required = false) Integer ano, @PathVariable(required = false) Integer mes) {
        List<Despesas> despesas = despesasRepository.findByAnoAndMes(ano,mes);
        if (despesas != null && !despesas.isEmpty()){
            return ResponseEntity.ok(despesas);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<Despesas> despesas = despesasRepository.findById(id);
        if (despesas.isPresent()) {
            despesasRepository.delete(despesas.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody @Valid DadosDespesas dadosDespesas) {

        // Consultar por despesas com a mesma descrição e o mesmo mês
        List<Despesas> despesasExistentes = despesasRepository.findByDescricaoAndMesAndAno(dadosDespesas.descricao(), dadosDespesas.mes(), dadosDespesas.ano());

        // Se já existir uma despesa com a mesma descrição e mês, retorna um erro 409
        if (despesasExistentes != null && !despesasExistentes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Já existe uma receita com a mesma descrição neste mês.");
        } else {


            Despesas despesasAtual = despesasRepository.findById(id).get();

            despesasAtual.setDescricao(dadosDespesas.descricao());
            despesasAtual.setMes(dadosDespesas.mes());
            despesasAtual.setValor(dadosDespesas.valor());
            despesasAtual.setCategoria(dadosDespesas.categoria());

            despesasRepository.save(despesasAtual);
            return ResponseEntity.status(HttpStatus.OK).body(despesasAtual);

        }

    }


}
