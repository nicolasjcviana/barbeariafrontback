package com.barbearia.barbeariasrnc.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import com.barbearia.barbeariasrnc.security.AES;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.barbearia.barbeariasrnc.model.Funcionario;
import com.barbearia.barbeariasrnc.service.FuncionarioService;

@RestController
@RequestMapping("/barbearia")
public class FuncionarioController {

	@Autowired
	FuncionarioService funcionarioService;
	
	/* Para salvar um funcionário */
	@PostMapping("/funcionarios")
	public Funcionario createFuncionario(@Valid @RequestBody Funcionario funcionario, @RequestHeader("Hash") String hash){

		if(! (funcionario.getNmFuncionario() + funcionario.getDsEmail()).equals(AES.decrypt(hash))){
			throw new RuntimeException("Erro ao validar o hash da requisição");
		}
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
