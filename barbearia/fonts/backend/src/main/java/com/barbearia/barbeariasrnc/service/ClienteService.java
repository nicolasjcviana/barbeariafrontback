package com.barbearia.barbeariasrnc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barbearia.barbeariasrnc.model.Cliente;
import com.barbearia.barbeariasrnc.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	/* Para salvar um cliente */
	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	/* Buscar todos os clientes */
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	/* Pegar um cliente pelo id */
	public Cliente findOne(Long id) {
		Cliente cliente = clienteRepository.findOne(id);
		return cliente;
	}
	
	/* Deletar um cliente pelo id */
	public void delete(Cliente cliente) {
		clienteRepository.delete(cliente);
	}
	
}
