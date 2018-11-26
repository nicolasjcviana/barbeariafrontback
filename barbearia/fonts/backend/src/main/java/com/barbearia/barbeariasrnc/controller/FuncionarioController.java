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

import com.barbearia.barbeariasrnc.model.Funcionario;
import com.barbearia.barbeariasrnc.service.FuncionarioService;

@RestController
@RequestMapping("/barbearia")
public class FuncionarioController {

	@Autowired
	FuncionarioService funcionarioService;
	
	/* Para salvar um funcionário */
	@PostMapping("/funcionarios")
	public Funcionario createFuncionario(@Valid @RequestBody Funcionario funcionario) {
		return funcionarioService.save(funcionario);
	}
	
	/* Buscar todos os funcionários */
	@GetMapping("/funcionarios")
	public List<Funcionario> getAllFuncionarios() {
		return funcionarioService.findAll();
	}
	
	/* Pegar um funcionário pelo id */
	@GetMapping("/funcionarios/{id}")
	public ResponseEntity<Funcionario> getFuncionarioById(@PathVariable(value="id") Long id) {
		Funcionario funcionario = funcionarioService.findOne(id);
		if (funcionario == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(funcionario);
	}
	
	/* Atualizar um funcionário pelo id */
	@PutMapping("/funcionarios/{id}")
	public ResponseEntity<Funcionario> updateFuncionario(@PathVariable(value="id") Long id, @Valid @RequestBody Funcionario info) {
		Funcionario funcionario = funcionarioService.findOne(id);
		if (funcionario == null) {
			return ResponseEntity.notFound().build();
		}
		BeanUtils.copyProperties(info, funcionario, "id");
		Funcionario novo = funcionarioService.save(funcionario);
		return ResponseEntity.ok().body(novo);
	}
	
	/* Deletar um funcionário pelo id */
	@DeleteMapping("/funcionarios/{id}")
	public ResponseEntity<Funcionario> deleteFuncionario(@PathVariable(value="id") Long id) {
		Funcionario funcionario = funcionarioService.findOne(id);
		if (funcionario == null) {
			return ResponseEntity.notFound().build();
		}
		funcionarioService.delete(funcionario);
		return ResponseEntity.ok().build();
	}
	
}
