package com.barbearia.barbeariasrnc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barbearia.barbeariasrnc.model.ItemAtendimento;

@Repository
public interface ItemAtendimentoRepository extends JpaRepository<ItemAtendimento, Long> {

}
