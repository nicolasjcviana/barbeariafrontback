package com.barbearia.barbeariasrnc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

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

import com.barbearia.barbeariasrnc.model.Servico;
import com.barbearia.barbeariasrnc.service.ServicoService;

@RestController
@RequestMapping("/barbearia")
public class ServicoController {
	
	@Autowired
	ServicoService servicoService;
	
	/* Para salvar um serviço */
	@PostMapping("/servicos")
	public Servico createServico(@Valid @RequestBody Servico servico) {
		return servicoService.save(servico);
	}
	
	/* Buscar todos os serviços */
	@GetMapping("/servicos")
	public List<Servico> getAllServicos() {
		return servicoService.findAll();
	}

	/* Buscar todos os serviços */
	@GetMapping("/servicosPorNome/{nome}")
	public List<Servico> getAllServicos(@PathVariable String nome) {
		List<Servico> listagem = servicoService.listarPorNome(nome);
		return listagem == null || listagem.isEmpty() ? new ArrayList<>() : listagem;
	}
	
	/* Pegar um serviço pelo id */
	@GetMapping("/servicos/{id}")
	public ResponseEntity<Servico> getServicoById(@PathVariable(value="id") Long id) {
		Servico servico = servicoService.findOne(id);
		if (servico == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(servico);
	}
	
	/* Atualizar um serviço pelo id */
	@PutMapping("/servicos/{id}")
	public ResponseEntity<Servico> updateServico(@PathVariable(value="id") Long id, @Valid @RequestBody Servico info) {
		Servico servico = servicoService.findOne(id);
		if (servico == null) {
			return ResponseEntity.notFound().build();
		}
		BeanUtils.copyProperties(info, servico, "id");
		Servico novo = servicoService.save(servico);
		return ResponseEntity.ok().body(novo);
	}
	
	/* Deletar um serviço pelo id */
	@DeleteMapping("/servicos/{id}")
	public ResponseEntity<Servico> deleteServico(@PathVariable(value="id") Long id) {
		Servico servico = servicoService.findOne(id);
		if (servico == null) {
			return ResponseEntity.notFound().build();
		}
		servicoService.delete(servico);
		return ResponseEntity.ok().build();
	}
	
}
