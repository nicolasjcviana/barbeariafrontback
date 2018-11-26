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
@Table(name="funcionarios")
@EntityListeners(AuditingEntityListener.class)
public class Funcionario {

	@Id
	@Column(name="cd_funcionario")
	@SequenceGenerator(name="funcionarios_cd_funcionario_seq", sequenceName="funcionarios_cd_funcionario_seq", allocationSize=1)
	@GeneratedValue(generator="funcionarios_cd_funcionario_seq", strategy=GenerationType.SEQUENCE)
	private Long cdFuncionario;
	
	@Column(name="nm_funcionario")
	private String nmFuncionario;
	
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
	
	@Column(name="nm_usuario")
	private String nmUsuario;
	
	@Column(name="ds_senha")
	private String dsSenha;
	
	@Column(name="cm_imagem")
	private String cmImagem;
	
	@Column(name="fl_tipo")
	private String flTipo;
	
	@Column(name="vl_salario")
	private Double vlSalario;

	public Long getCdFuncionario() {
		return cdFuncionario;
	}

	public void setCdFuncionario(Long cdFuncionario) {
		this.cdFuncionario = cdFuncionario;
	}

	public String getNmFuncionario() {
		return nmFuncionario;
	}

	public void setNmFuncionario(String nmFuncionario) {
		this.nmFuncionario = nmFuncionario;
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

	public String getNmUsuario() {
		return nmUsuario;
	}

	public void setNmUsuario(String nmUsuario) {
		this.nmUsuario = nmUsuario;
	}

	public String getDsSenha() {
		return dsSenha;
	}

	public void setDsSenha(String dsSenha) {
		this.dsSenha = dsSenha;
	}

	public String getCmImagem() {
		return cmImagem;
	}

	public void setCmImagem(String cmImagem) {
		this.cmImagem = cmImagem;
	}

	public String getFlTipo() {
		return flTipo;
	}

	public void setFlTipo(String flTipo) {
		this.flTipo = flTipo;
	}

	public Double getVlSalario() {
		return vlSalario;
	}

	public void setVlSalario(Double vlSalario) {
		this.vlSalario = vlSalario;
	}
	
}
