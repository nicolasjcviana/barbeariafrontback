package com.barbearia.barbeariasrnc.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barbearia.barbeariasrnc.model.Atendimento;
import com.barbearia.barbeariasrnc.model.Cliente;
import com.barbearia.barbeariasrnc.model.ClienteAtendimento;
import com.barbearia.barbeariasrnc.model.ItemAtendimento;
import com.barbearia.barbeariasrnc.repository.AtendimentoRepository;

@Service
public class AtendimentoService {

	@Autowired
	AtendimentoRepository atendimentoRepository;
	
	@Autowired
	ClienteService clienteService;
	
	/* Para salvar um atendimento */
	public Atendimento save(Atendimento atendimento) {
		atendimento.setVlTotal(atendimento.getItensAtendimento().stream().mapToDouble(ItemAtendimento::getVlServico).sum());
		atendimento.setDtAtendimento(LocalDate.now());
		atendimento.getItensAtendimento().forEach(o -> o.setCdAtendimento(atendimento));
		return atendimentoRepository.save(atendimento);
	}
	
	/* Buscar todos os atendimentos */
	public List<Atendimento> findAll() {
		return atendimentoRepository.findAll();
	}
	
	/* Buscar todos os atendimentos separados por cliente */
	public List<ClienteAtendimento> getAtendimentosCliente() {
		List<ClienteAtendimento> atendimentosCliente = new ArrayList<>();
		List<Cliente> clientes = clienteService.findAll();
		for (Cliente cliente : clientes) {
			ClienteAtendimento clienteAtendimento = new ClienteAtendimento();
			clienteAtendimento.setCliente(cliente);
			List<Atendimento> atendimentos = findByCliente(cliente);
			clienteAtendimento.setAtendimentos(atendimentos);
			atendimentosCliente.add(clienteAtendimento);
		}
		return atendimentosCliente;
	}
	
	/* Obter o valor total dos atendimentos por período */
	public Double getValorTotalAtendimentos(LocalDate dtInicio, LocalDate dtFim) {
		Double receitasPeriodo = atendimentoRepository.getReceitasPeriodo(dtInicio, dtFim);
		return receitasPeriodo == null ? 0 : receitasPeriodo;
	}
	
	/* Obter o valor total dos atendimentos por período */
	public Double getValorTotalSalarios() {
		Double despesasPeriodo = atendimentoRepository.getDespesasPeriodo();
		return despesasPeriodo == null ? 0 : despesasPeriodo;
	}
	
	/* Buscar todos os atendimentos de um cliente */
	public List<Atendimento> findByCliente(Cliente cliente) {
		return atendimentoRepository.findByCdCliente(cliente);
	}
	
	/* Pegar um atendimento pelo id */
	public Atendimento findOne(Long id) {
		Atendimento atendimento = atendimentoRepository.findOne(id);
		return atendimento;
	}
	
	/* Deletar um atendimento pelo id */
	public void delete(Atendimento atendimento) {
		atendimentoRepository.delete(atendimento);
	}
	
}
