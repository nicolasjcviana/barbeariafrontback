package com.barbearia.barbeariasrnc.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.barbearia.barbeariasrnc.model.Atendimento;
import com.barbearia.barbeariasrnc.model.Cliente;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

	List<Atendimento> findByCdCliente(Cliente cliente);

	@Query(value = "SELECT	sum(vl_total)"
				+ " FROM	atendimentos"
				+ " WHERE	dt_atendimento between ? AND ?", nativeQuery = true)
	Double getReceitasPeriodo(LocalDate dtInicio, LocalDate dtFim);
	
	@Query(value = "select sum(vl_salario) " + 
			"from salarios " + 
			"inner join (" + 
			"	select max(cd_salario) as cd_salario, cd_funcionario" + 
			"	from salarios sal2" + 
			"	group by cd_funcionario" + 
			") sals on salarios.cd_salario = sals.cd_salario", nativeQuery = true)
	Double getDespesasPeriodo();//Ajustar select

}
