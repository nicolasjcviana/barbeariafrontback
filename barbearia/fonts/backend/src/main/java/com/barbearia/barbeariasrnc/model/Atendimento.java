package com.barbearia.barbeariasrnc.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "atendimentos")
@EntityListeners(AuditingEntityListener.class)
public class Atendimento {

	@Id
	@Column(name = "cd_atendimento")
	@SequenceGenerator(name = "atendimentos_cd_atendimento_seq", sequenceName = "atendimentos_cd_atendimento_seq", allocationSize = 1)
	@GeneratedValue(generator = "atendimentos_cd_atendimento_seq", strategy = GenerationType.SEQUENCE)
	private Long cdAtendimento;

	@ManyToOne
	@JoinColumn(name = "cd_cliente")
	private Cliente cdCliente;

	@ManyToOne
	@JoinColumn(name = "cd_funcionario")
	private Funcionario cdFuncionario;

	@Column(name = "dt_atendimento")
	private LocalDate dtAtendimento;

	@Column(name = "vl_total")
	private Double vlTotal;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cdAtendimento")
	private List<ItemAtendimento> itensAtendimento = new ArrayList<>();

	public Long getCdAtendimento() {
		return cdAtendimento;
	}

	public void setCdAtendimento(Long cdAtendimento) {
		this.cdAtendimento = cdAtendimento;
	}

	public Cliente getCdCliente() {
		return cdCliente;
	}

	public void setCdCliente(Cliente cdCliente) {
		this.cdCliente = cdCliente;
	}

	public Funcionario getCdFuncionario() {
		return cdFuncionario;
	}

	public void setCdFuncionario(Funcionario cdFuncionario) {
		this.cdFuncionario = cdFuncionario;
	}

	public LocalDate getDtAtendimento() {
		return dtAtendimento;
	}

	public void setDtAtendimento(LocalDate dtAtendimento) {
		this.dtAtendimento = dtAtendimento;
	}

	public Double getVlTotal() {
		return vlTotal;
	}

	public void setVlTotal(Double vlTotal) {
		this.vlTotal = vlTotal;
	}

	public List<ItemAtendimento> getItensAtendimento() {
		return itensAtendimento;
	}

	public void setItensAtendimento(List<ItemAtendimento> itensAtendimento) {
		this.itensAtendimento = itensAtendimento;
	}

}
