/*DROP TABLE schema_version;

DROP TRIGGER hist_func_sal_update ON funcionarios;

DROP FUNCTION hist_func_sal;

DROP TABLE salarios;

DROP TABLE itens_atendimento;

DROP TABLE atendimentos;

DROP TABLE servicos;

DROP TABLE funcionarios;

DROP TABLE clientes;*/

CREATE TABLE clientes (
  cd_cliente BIGSERIAL NOT NULL,
  nm_cliente VARCHAR(45) NOT NULL,
  ds_email VARCHAR(45) NULL,
  nr_telefone VARCHAR(20) NULL,
  dt_nascimento DATE NULL,
  ds_endereco VARCHAR(45) NULL,
  ds_observacao VARCHAR(500) NULL,
  PRIMARY KEY (cd_cliente)
);

CREATE TABLE funcionarios (
  cd_funcionario BIGSERIAL NOT NULL,
  nm_funcionario VARCHAR(45) NOT NULL,
  ds_email VARCHAR(45) NOT NULL,
  nr_telefone VARCHAR(20) NOT NULL,
  dt_nascimento DATE NOT NULL,
  ds_endereco VARCHAR(45) NOT NULL,
  ds_observacao VARCHAR(500) NULL,
  nm_usuario VARCHAR(15) NULL,
  ds_senha VARCHAR(30) NULL,
  cm_imagem VARCHAR(100) NULL,
  fl_tipo VARCHAR(5) NOT NULL,
  vl_salario DOUBLE PRECISION NOT NULL,
  PRIMARY KEY (cd_funcionario)
);

CREATE TABLE servicos (
  cd_servico BIGSERIAL NOT NULL,
  ds_servico VARCHAR(45) NOT NULL,
  vl_servico DOUBLE PRECISION NULL,
  PRIMARY KEY (cd_servico)
);

CREATE TABLE atendimentos (
  cd_atendimento BIGSERIAL NOT NULL,
  cd_cliente BIGSERIAL NOT NULL,
  cd_funcionario BIGSERIAL NOT NULL,
  dt_atendimento DATE NULL,
  vl_total DOUBLE PRECISION NULL,
  PRIMARY KEY (cd_atendimento),
  CONSTRAINT fk_atend_cli
    FOREIGN KEY (cd_cliente)
    REFERENCES clientes (cd_cliente),
  CONSTRAINT fk_atend_func
    FOREIGN KEY (cd_funcionario)
    REFERENCES funcionarios (cd_funcionario)
);

CREATE TABLE itens_atendimento (
  cd_item BIGSERIAL NOT NULL,
  cd_atendimento BIGSERIAL NOT NULL,
  cd_servico BIGSERIAL NOT NULL,
  vl_servico DOUBLE PRECISION NULL,
  CONSTRAINT fk_it_atend
    FOREIGN KEY (cd_atendimento)
    REFERENCES atendimentos (cd_atendimento),
  CONSTRAINT fk_it_serv
    FOREIGN KEY (cd_servico)
    REFERENCES servicos (cd_servico)
);

CREATE TABLE salarios (
  cd_salario BIGSERIAL NOT NULL,
  cd_funcionario BIGSERIAL NOT NULL,
  dt_atualizacao DATE NOT NULL,
  vl_salario DOUBLE PRECISION NOT NULL,
  PRIMARY KEY (cd_salario),
  CONSTRAINT fk_sal_func
    FOREIGN KEY (cd_funcionario)
    REFERENCES funcionarios (cd_funcionario)
);

CREATE OR REPLACE FUNCTION hist_func_sal()
  RETURNS TRIGGER AS $func_sal$
  BEGIN
    INSERT INTO salarios (cd_salario, cd_funcionario, dt_atualizacao, vl_salario)
    VALUES (NEXTVAL('salarios_cd_salario_seq'), NEW.cd_funcionario, CURRENT_DATE, NEW.vl_salario);
  RETURN NEW;
  END;
  $func_sal$ LANGUAGE plpgsql;

CREATE TRIGGER hist_func_sal_update
  AFTER INSERT OR UPDATE ON funcionarios
  FOR EACH ROW
  EXECUTE PROCEDURE hist_func_sal();