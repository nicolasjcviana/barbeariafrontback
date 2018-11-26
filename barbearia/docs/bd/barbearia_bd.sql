CREATE TABLE clientes (
  cd_cliente INT NOT NULL AUTO_INCREMENT,
  nm_cliente VARCHAR(45) NOT NULL,
  ds_email VARCHAR(45) NULL,
  nr_telefone VARCHAR(20) NULL,
  dt_nascimento DATE NULL,
  ds_endereco VARCHAR(45) NULL,
  ds_observacao VARCHAR(500) NULL,
  PRIMARY KEY (cd_cliente)
);

CREATE TABLE funcionarios (
  cd_funcionario INT NOT NULL AUTO_INCREMENT,
  nm_funcionario VARCHAR(45) NOT NULL,
  ds_email VARCHAR(45) NOT NULL,
  nr_telefone VARCHAR(20) NOT NULL,
  dt_nascimento DATE NOT NULL,
  ds_endereco VARCHAR(45) NOT NULL,
  ds_observacao VARCHAR(500) NULL,
  nm_usuario VARCHAR(15) NULL,
  ds_senha VARCHAR(30) NULL,
  cm_imagem VARCHAR(100) NOT NULL,
  fl_tipo VARCHAR(5) NOT NULL,
  PRIMARY KEY (cd_funcionario)
);

CREATE TABLE servicos (
  cd_servico INT NOT NULL AUTO_INCREMENT,
  ds_servico VARCHAR(45) NOT NULL,
  vl_servico FLOAT(8,2) NULL,
  PRIMARY KEY (cd_servico)
);

CREATE TABLE atendimentos (
  cd_atendimento INT NOT NULL AUTO_INCREMENT,
  cd_cliente INT NOT NULL,
  cd_funcionario INT NOT NULL,
  dt_atendimento DATE NULL,
  vl_total FLOAT(8,2) NULL,
  PRIMARY KEY (cd_atendimento) ,
  CONSTRAINT fk_atend_cli
    FOREIGN KEY (cd_cliente)
    REFERENCES clientes (cd_cliente),
  CONSTRAINT fk_atend_func
    FOREIGN KEY (cd_funcionario)
    REFERENCES funcionarios (cd_funcionario)
);

CREATE TABLE itens_atendimento (
  cd_atendimento INT NOT NULL,
  cd_servico INT NOT NULL,
  vl_servico FLOAT(8,2) NULL,
  PRIMARY KEY (cd_atendimento, cd_servico),
  CONSTRAINT fk_it_atend
    FOREIGN KEY (cd_atendimento)
    REFERENCES atendimentos (cd_atendimento),
  CONSTRAINT fk_it_serv
    FOREIGN KEY (cd_servico)
    REFERENCES servicos (cd_servico)
);