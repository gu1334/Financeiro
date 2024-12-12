package com.financeiro.financeiro.domain.despesas;

import com.financeiro.financeiro.domain.receitas.Receitas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DespesasRepository extends JpaRepository<Despesas, Long> {
    List<Despesas> findByDescricaoAndMesAndAno(String descricao, int mes, int ano);

    List<Despesas> findByDescricao(String descricao);

    List<Despesas> findByAnoAndMes(int ano, int mes);


    @Query("""
    select sum(d.valor) from Despesas d
    where d.ano = :ano
    and d.mes = :mes
""")
    Long findTotalByAnoAndMes(@Param("ano") int ano, @Param("mes") int mes);


    @Query("""
select d.categoria, sum(d.valor)
from Despesas d
where d.ano = :ano
and d.mes = :mes
group by d.categoria
""")
    List<Object[]> findTotalPorCategoria(@Param("ano") int ano, @Param("mes") int mes);

}
