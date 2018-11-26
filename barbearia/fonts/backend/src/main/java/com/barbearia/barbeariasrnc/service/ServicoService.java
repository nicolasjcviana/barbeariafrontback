package com.barbearia.barbeariasrnc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barbearia.barbeariasrnc.model.Servico;
import com.barbearia.barbeariasrnc.repository.ServicoRepository;

@Service
public class ServicoService {

	@Autowired
	ServicoRepository servicoRepository;
	
	/* Para salvar um serviço */
	public Servico save(Servico servico) {
		return servicoRepository.save(servico);
	}
	
	/* Buscar todos os serviços */
	public List<Servico> findAll() {
		return servicoRepository.findAll();
	}
	
	/* Pegar um serviço pelo id */
	public Servico findOne(Long id) {
		Servico servico = servicoRepository.findOne(id);
		return servico;
	}
	
	/* Deletar um serviço pelo id */
	public void delete(Servico servico) {
		servicoRepository.delete(servico);
	}

	public List<Servico> listarPorNome(String nome) {
		return servicoRepository.findByDsServicoContainingIgnoreCase(nome);
	}
	
}
