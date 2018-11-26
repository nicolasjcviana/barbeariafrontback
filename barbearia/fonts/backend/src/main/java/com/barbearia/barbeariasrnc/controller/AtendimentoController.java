package com.barbearia.barbeariasrnc.controller;

import java.time.LocalDate;
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

import com.barbearia.barbeariasrnc.model.Atendimento;
import com.barbearia.barbeariasrnc.model.ClienteAtendimento;
import com.barbearia.barbeariasrnc.service.AtendimentoService;

@RestController
@RequestMapping("/barbearia")
public class AtendimentoController {

	@Autowired
	AtendimentoService atendimentoService;
	
	/* Para salvar um atendimento */
	@PostMapping("/atendimentos")
	public Atendimento createAtendimento(@Valid @RequestBody Atendimento atendimento) {
		return atendimentoService.save(atendimento);
	}
	
	/* Buscar todos os atendimentos separados por cliente */
	@GetMapping("/atendimentosCliente")
	public List<ClienteAtendimento> getAtendimentosCliente() {
		return atendimentoService.getAtendimentosCliente();
	}
	
	/* Buscar todos os atendimentos */
	@GetMapping("/atendimentos")
	public List<Atendimento> getAllAtendimentos() {
		return atendimentoService.findAll();
	}

	/* Obter o valor total dos atendimentos no mês atual */
	@GetMapping("/atendimentos/totalReceitas")
	public Double getValorTotalReceitas() {
		LocalDate dataAtual = LocalDate.now();
		LocalDate dtFim = dataAtual.withDayOfMonth(dataAtual.lengthOfMonth());
		LocalDate dtInicio = dataAtual.withDayOfMonth(1);
		return atendimentoService.getValorTotalAtendimentos(dtInicio, dtFim);
	}
	
	/* Obter o valor total de salários dos funcionários no mês atual */
	@GetMapping("/atendimentos/totalDespesas")
	public Double getValorTotalDespesas() {
		return atendimentoService.getValorTotalSalarios();
	}
	
	/* Obter o lucro total do mês atual */
	@GetMapping("/atendimentos/lucroTotal")
	public Double getLucroTotal() {
		return getValorTotalReceitas() - getValorTotalDespesas();
	}
	
	/* Pegar um atendimento pelo id */
	@GetMapping("/atendimentos/{id}")
	public ResponseEntity<Atendimento> getAtendimentoById(@PathVariable(value="id") Long id) {
		Atendimento atendimento = atendimentoService.findOne(id);
		if (atendimento == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(atendimento);
	}
	
	/* Atualizar um atendimento pelo id */
	@PutMapping("/atendimentos/{id}")
	public ResponseEntity<Atendimento> updateAtendimento(@PathVariable(value="id") Long id, @Valid @RequestBody Atendimento info) {
		Atendimento atendimento = atendimentoService.findOne(id);
		if (atendimento == null) {
			return ResponseEntity.notFound().build();
		}
		BeanUtils.copyProperties(info, atendimento, "id");
		Atendimento novo = atendimentoService.save(atendimento);
		return ResponseEntity.ok().body(novo);
	}
	
	/* Deletar um atendimento pelo id */
	@DeleteMapping("/atendimentos/{id}")
	public ResponseEntity<Atendimento> deleteAtendimento(@PathVariable(value="id") Long id) {
		Atendimento atendimento = atendimentoService.findOne(id);
		if (atendimento == null) {
			return ResponseEntity.notFound().build();
		}
		atendimentoService.delete(atendimento);
		return ResponseEntity.ok().build();
	}
	
}
