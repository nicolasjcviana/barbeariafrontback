package com.barbearia.barbeariasrnc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barbearia.barbeariasrnc.model.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

	List<Servico> findByDsServicoContaining(String nome);

	List<Servico> findByDsServicoContainingIgnoreCase(String nome);

}
