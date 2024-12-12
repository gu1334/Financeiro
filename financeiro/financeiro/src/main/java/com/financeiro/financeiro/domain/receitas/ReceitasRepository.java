package com.financeiro.financeiro.domain.receitas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReceitasRepository extends JpaRepository<Receitas, Long> {

    List<Receitas> findByDescricao(String descricao);

    List<Receitas> findByDescricaoAndMesAndAno(String descricao, int mes, int ano);
    List<Receitas> findByAnoAndMes(int ano, int mes);

    List<Receitas> findByAnoAndMesAndValor(int ano,int mes, int valor);
    @Query("""
    select sum(r.valor) from Receitas r
    where r.ano = :ano
    and r.mes = :mes
""")
    Long findTotalByAnoAndMes(@Param("ano") int ano, @Param("mes") int mes);



}
