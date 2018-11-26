package com.barbearia.barbeariasrnc.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="clientes")
@EntityListeners(AuditingEntityListener.class)
public class Cliente {

	@Id
	@Column(name="cd_cliente")
	@SequenceGenerator(name="clientes_cd_cliente_seq", sequenceName="clientes_cd_cliente_seq", allocationSize=1)
	@GeneratedValue(generator="clientes_cd_cliente_seq", strategy=GenerationType.SEQUENCE)
	private Long cdCliente;
	
	@Column(name="nm_cliente")
	private String nmCliente;
	
	@Column(name="ds_email")
	private String dsEmail;
	
	@Column(name="nr_telefone")
	private String nrTelefone;
	
	@Column(name="dt_nascimento")
	private LocalDate dtNascimento;
	
	@Column(name="ds_endereco")
	private String dsEndereco;
	
	@Column(name="ds_observacao")
	private String dsObservacao;

	public Long getCdCliente() {
		return cdCliente;
	}

	public void setCdCliente(Long cdCliente) {
		this.cdCliente = cdCliente;
	}

	public String getNmCliente() {
		return nmCliente;
	}

	public void setNmCliente(String nmCliente) {
		this.nmCliente = nmCliente;
	}

	public String getDsEmail() {
		return dsEmail;
	}

	public void setDsEmail(String dsEmail) {
		this.dsEmail = dsEmail;
	}

	public String getNrTelefone() {
		return nrTelefone;
	}

	public void setNrTelefone(String nrTelefone) {
		this.nrTelefone = nrTelefone;
	}

	public LocalDate getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(LocalDate dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getDsEndereco() {
		return dsEndereco;
	}

	public void setDsEndereco(String dsEndereco) {
		this.dsEndereco = dsEndereco;
	}

	public String getDsObservacao() {
		return dsObservacao;
	}

	public void setDsObservacao(String dsObservacao) {
		this.dsObservacao = dsObservacao;
	}

	
	
}
