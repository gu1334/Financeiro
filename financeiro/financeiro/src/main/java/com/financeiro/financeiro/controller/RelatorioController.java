package com.financeiro.financeiro.controller;

import com.financeiro.financeiro.domain.despesas.DespesasRepository;
import com.financeiro.financeiro.domain.receitas.ReceitasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/resumo")
public class RelatorioController {

    @Autowired
    DespesasRepository despesasRepository;

    @Autowired
    ReceitasRepository receitasRepository;


    @GetMapping("/{ano}/{mes}")
    public ResponseEntity<String> relatorio(@PathVariable int ano, @PathVariable int mes) {
        Long totalDespesas = despesasRepository.findTotalByAnoAndMes(ano, mes);
        Long totalReceitas = receitasRepository.findTotalByAnoAndMes(ano, mes);

        List<Object[]> totaisPorCategoria = despesasRepository.findTotalPorCategoria(ano, mes);

        Long saldo = totalDespesas - totalReceitas;

        Map<String, Double> resultado = new HashMap<>();
        for (Object[] item : totaisPorCategoria) {
            String categoria = (String) item[0]; // Categoria
            Double total = (Double) item[1]; // Total de despesas
            resultado.put(categoria, total);
        }



        return ResponseEntity.ok("O relatorio do mês " + mes +" e do ano de: " + ano + "Foi de : " + "\nTotal de Despesas: " + totalDespesas + "\nTotal de Receitas: " + totalReceitas + "\nSaldo: " + saldo + "\nE os gastos por categoria foram: " + resultado);

    }



}
