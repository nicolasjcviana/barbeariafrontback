package com.barbearia.barbeariasrnc.model;

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
@Table(name = "servicos")
@EntityListeners(AuditingEntityListener.class)
public class Servico {

	@Id
	@Column(name = "cd_servico")
	@SequenceGenerator(name = "servicos_cd_servico_seq", sequenceName = "servicos_cd_servico_seq", allocationSize = 1)
	@GeneratedValue(generator = "servicos_cd_servico_seq", strategy = GenerationType.SEQUENCE)
	private Long cdServico;

	@Column(name = "ds_servico")
	private String dsServico;

	@Column(name = "vl_servico")
	private Double vlServico;

	public Long getCdServico() {
		return cdServico;
	}

	public void setCdServico(Long cdServico) {
		this.cdServico = cdServico;
	}

	public String getDsServico() {
		return dsServico;
	}

	public void setDsServico(String dsServico) {
		this.dsServico = dsServico;
	}

	public Double getVlServico() {
		return vlServico;
	}

	public void setVlServico(Double vlServico) {
		this.vlServico = vlServico;
	}

}
