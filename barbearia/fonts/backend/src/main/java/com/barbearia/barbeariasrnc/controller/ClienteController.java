package com.barbearia.barbeariasrnc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barbearia.barbeariasrnc.model.Cliente;
import com.barbearia.barbeariasrnc.service.ClienteService;

@RestController
@RequestMapping("/barbearia")
public class ClienteController {

	@Autowired
	ClienteService clienteService;
	
	/* Para salvar um cliente */
	@PostMapping("/clientes")
	public Cliente createCliente(@Valid @RequestBody Cliente cliente) {
		return clienteService.save(cliente);
	}
	
	/* Buscar todos os clientes */
	@GetMapping("/clientes")
	public List<Cliente> getAllClientes() {
		return clienteService.findAll();
	}
	
	/* Pegar um cliente pelo id */
	@GetMapping("/clientes/{id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable(value="id") Long id) {
		Cliente cliente = clienteService.findOne(id);
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(cliente);
	}
	
	/* Atualizar um cliente pelo id */
	@PutMapping("/clientes/{id}")
	public ResponseEntity<Cliente> updateCliente(@PathVariable(value="id") Long id, @Valid @RequestBody Cliente info) {
		Cliente cliente = clienteService.findOne(id);
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		}
		BeanUtils.copyProperties(info, cliente, "id");
		Cliente novo = clienteService.save(cliente);
		return ResponseEntity.ok().body(novo);
	}
	
	/* Deletar um cliente pelo id */
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<Cliente> deleteCliente(@PathVariable(value="id") Long id) {
		Cliente cliente = clienteService.findOne(id);
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		}
		clienteService.delete(cliente);
		return ResponseEntity.ok().build();
	}
	
}
