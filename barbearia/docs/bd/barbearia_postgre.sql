CREATE SEQUENCE clientes_seq;

CREATE TABLE clientes (
  cd_cliente BIGSERIAL NOT NULL DEFAULT NEXTVAL ('clientes_seq'),
  nm_cliente VARCHAR(45) NOT NULL,
  ds_email VARCHAR(45) NULL,
  nr_telefone VARCHAR(20) NULL,
  dt_nascimento DATE NULL,
  ds_endereco VARCHAR(45) NULL,
  ds_observacao VARCHAR(500) NULL,
  PRIMARY KEY (cd_cliente)
);

CREATE SEQUENCE funcionarios_seq;

CREATE TABLE funcionarios (
  cd_funcionario BIGSERIAL NOT NULL DEFAULT NEXTVAL ('funcionarios_seq'),
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

CREATE SEQUENCE servicos_seq;

CREATE TABLE servicos (
  cd_servico BIGSERIAL NOT NULL DEFAULT NEXTVAL ('servicos_seq'),
  ds_servico VARCHAR(45) NOT NULL,
  vl_servico DOUBLE PRECISION NULL,
  PRIMARY KEY (cd_servico)
);

CREATE SEQUENCE atendimentos_seq;

CREATE TABLE atendimentos (
  cd_atendimento BIGSERIAL NOT NULL DEFAULT NEXTVAL ('atendimentos_seq'),
  cd_cliente BIGSERIAL NOT NULL,
  cd_funcionario BIGSERIAL NOT NULL,
  dt_atendimento DATE NULL,
  vl_total DOUBLE PRECISION NULL,
  PRIMARY KEY (cd_atendimento) ,
  CONSTRAINT fk_atend_cli
    FOREIGN KEY (cd_cliente)
    REFERENCES clientes (cd_cliente),
  CONSTRAINT fk_atend_func
    FOREIGN KEY (cd_funcionario)
    REFERENCES funcionarios (cd_funcionario)
);

CREATE TABLE itens_atendimento (
  cd_atendimento BIGSERIAL NOT NULL,
  cd_servico BIGSERIAL NOT NULL,
  vl_servico DOUBLE PRECISION NULL,
  PRIMARY KEY (cd_atendimento, cd_servico),
  CONSTRAINT fk_it_atend
    FOREIGN KEY (cd_atendimento)
    REFERENCES atendimentos (cd_atendimento),
  CONSTRAINT fk_it_serv
    FOREIGN KEY (cd_servico)
    REFERENCES servicos (cd_servico)
);