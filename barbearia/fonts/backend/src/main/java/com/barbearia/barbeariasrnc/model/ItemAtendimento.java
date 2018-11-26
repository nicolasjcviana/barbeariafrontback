package com.barbearia.barbeariasrnc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "itens_atendimento")
@EntityListeners(AuditingEntityListener.class)
public class ItemAtendimento {

	@Id
	@Column(name = "cd_item")
	@SequenceGenerator(name = "itens_atendimento_cd_item_seq", sequenceName = "itens_atendimento_cd_item_seq", allocationSize = 1)
	@GeneratedValue(generator = "itens_atendimento_cd_item_seq", strategy = GenerationType.SEQUENCE)
	private Long cdItem;

	@ManyToOne
	@JoinColumn(name = "cd_atendimento")
	@JsonIgnore
	private Atendimento cdAtendimento;

	@ManyToOne
	@JoinColumn(name = "cd_servico")
	private Servico cdServico;

	@Column(name = "vl_servico")
	private Double vlServico;

	public Long getCdItem() {
		return cdItem;
	}

	public void setCdItem(Long cdItem) {
		this.cdItem = cdItem;
	}

	public Atendimento getCdAtendimento() {
		return cdAtendimento;
	}

	public void setCdAtendimento(Atendimento cdAtendimento) {
		this.cdAtendimento = cdAtendimento;
	}

	public Servico getCdServico() {
		return cdServico;
	}

	public void setCdServico(Servico cdServico) {
		this.cdServico = cdServico;
	}

	public Double getVlServico() {
		return vlServico;
	}

	public void setVlServico(Double vlServico) {
		this.vlServico = vlServico;
	}

}
