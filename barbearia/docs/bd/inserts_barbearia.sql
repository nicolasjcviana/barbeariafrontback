insert into clientes (cd_cliente,nm_cliente,ds_email,nr_telefone,dt_nascimento,ds_endereco,ds_observacao) values
(1,'Antônio da Silva','tonho.silva@mail.com','(11) 99123-0123','1985-05-15','Rua das Palmeiras, 567','CUIDADO: Cliente com fama de caloteiro.'),
(2,'José Carlos','ze_carlos@mail.com','(47) 99100-2222','1991-02-03','Rua XV de Novembro, 421',''),
(3,'Marlon Brando','marlon.brando@mail.com','(47) 92403-1972','1924-04-03','Nova York','Interpretou Don Vito Corleone em O Poderoso Chefão.'),
(4,'Bruce Wayne','the.bruce@wayne.com','(47) 99391-5050','1939-05-27','Gotham City','Proprietário da Wayne Enterprises.'),
(5,'Paulo da Luz','paulo.light@mail.com','(11) 91100-3388','2000-06-16','Rua VII de Setembro, 520','');

insert into funcionarios (cd_funcionario,nm_funcionario,ds_email,nr_telefone,dt_nascimento,ds_endereco,ds_observacao,nm_usuario,ds_senha,fl_tipo,vl_salario) values
(1,'Zidane','q_zidane@mail.com','(11) 99888-7373','1972-06-23','França','','zizi','z123','G',8000);

insert into servicos (cd_servico,ds_servico,vl_servico) values
(1,'Corte de Cabelo',15),
(2,'Corte de Barba',8),
(3,'Corte de Cabelo (Neymar 1)',20),
(4,'Corte de Cabelo (Neymar 2)',12),
(5,'Corte de Cabelo (Neymar 3)',17);

/*select	'('||a.cd_cliente||','''||a.nm_cliente||''','''||a.ds_email||''','''||a.nr_telefone||''','''||a.dt_nascimento||''','''||a.ds_endereco||''','''||a.ds_observacao||''')' cliente
from	clientes a

select	'('||a.cd_funcionario||','''||a.nm_funcionario||''','''||a.ds_email||''','''||a.nr_telefone||''','''||a.dt_nascimento||''','''||a.ds_endereco||''','''||a.ds_observacao||''','''||a.nm_usuario||''','''||a.ds_senha||''','''||a.fl_tipo||''','||a.vl_salario||')' funcionario
from	funcionarios a

select	'('||a.cd_servico||','''||a.ds_servico||''','||a.vl_servico||')' servico
from	servicos a

select nextval('clientes_cd_cliente_seq')

select nextval('funcionarios_cd_funcionario_seq')

select nextval('servicos_cd_servico_seq')*/