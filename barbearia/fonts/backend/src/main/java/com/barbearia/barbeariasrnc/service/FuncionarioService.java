package com.barbearia.barbeariasrnc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barbearia.barbeariasrnc.model.Funcionario;
import com.barbearia.barbeariasrnc.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	/* Para salvar um funcionário */
	public Funcionario save(Funcionario funcionario) {
		funcionario.setDsSenha("123456"); // senha padrão
		return funcionarioRepository.save(funcionario);
	}
	
	/* Buscar todos os funcionários */
	public List<Funcionario> findAll() {
		return funcionarioRepository.findAll();
	}
	
	/* Pegar um funcionário pelo id */
	public Funcionario findOne(Long id) {
		Funcionario funcionario = funcionarioRepository.findOne(id);
		return funcionario;
	}
	
	/* Deletar um funcionário pelo id */
	public void delete(Funcionario funcionario) {
		funcionarioRepository.delete(funcionario);
	}
	
}
