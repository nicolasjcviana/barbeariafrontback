package com.barbearia.barbeariasrnc.model;

import java.util.ArrayList;
import java.util.List;

public class ClienteAtendimento {

	Cliente cliente;
	List<Atendimento> atendimentos;
	double valorTotal;
	
	public ClienteAtendimento() {
		this.cliente = null;
		this.atendimentos = new ArrayList<>();
		this.valorTotal = 0;
	}

	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<Atendimento> getAtendimentos() {
		return atendimentos;
	}
	
	public void addAtendimento(Atendimento atendimento) {
		atendimentos.add(atendimento);
		atualizarValorTotal(atendimento);
	}
	
	public void setAtendimentos(List<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
		atualizarValorTotal(atendimentos);
	}
	
	private void atualizarValorTotal(Atendimento atendimento) {
		valorTotal += atendimento.getVlTotal();
	}
	
	private void atualizarValorTotal(List<Atendimento> atendimentos) {
		for (Atendimento atendimento : atendimentos) {
			valorTotal += atendimento.getVlTotal();
		}
	}

	public double getValorTotal() {
		return valorTotal;
	}

}
