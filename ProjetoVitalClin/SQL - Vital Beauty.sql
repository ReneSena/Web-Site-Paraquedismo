create table tipoAcesso (
    id serial,
    descricao varchar(100),
    constraint tipoAcesso_pk primary key (id)
)

create table usuario (
    id serial,
    email varchar(100),
    senha varchar(20),
    tipoAcessoFK  integer,
    constraint pk_usuario primary key (id),
    constraint fk_tipoAcesso foreign key (tipoAcessoFK) references tipoAcesso (id)
);

create table cliente (
	id serial,
    nome varchar (100) not null,
    cpf varchar (15),
    dtNascimento varchar(20),
    sexo varchar(1),
    logradouro varchar(200),
    bairro varchar(100),
    numero integer,
    cidade varchar(100),
    uf varchar (5),
    cep varchar (20),
    telFixo varchar (20),
    celular varchar (20),
    situacao integer,
    usuarioFK integer,
    constraint pk_cliente primary key (id),
    constraint fk_usuario foreign key (UsuarioFK) references usuario (id)
    
);

-- Tabelas relacionadas ao funcionario

create table cargo(
    id serial,
    descricao varchar(100),
    constraint cargo_pk Primary Key (id)
);

create table contrato (
    id serial,
    descricao varchar(100),
    constraint contrato_pk primary key (id),
);

create table funcionario (
	id serial,
    nome varchar (100) not null,
    cpf varchar (15),
    dtNascimento varchar(20),
    sexo varchar(1),
    logradouro varchar(200),
    bairro varchar(100),
    numero integer,
    cidade varchar(100),
    uf varchar (5),
    cep varchar (20),
    telFixo varchar (20),
    celular varchar (20),
    situacao integer,
    usuarioFK integer,
    contratoFK integer,
    cargoFK integer,
    salario numeric (10,2),
    constraint pk_funcionario primary key (id),
    constraint fk_usuario foreign key (usuarioFK) references usuario (id),
    constraint fk_contrato foreign key (contratoFK) references contrato (id),
    constraint cargo_fk foreign key (cargoFK) references cargo (id)
    
);

-- Tabelas relacionadas ao agendamento

create table agendamento(
    id serial,
    data date,
    hora varchar (20),
    status varchar(20),
    situacao varchar(30),
    clientefk integer,
    tratamentofk integer,
    funcionariofk integer,
    constraint pk_agendamento primary key (id),
    constraint fk_Cliente foreign key (clientefk) references cliente (id),
    constraint fk_Tratamento foreign key (tratamentofk) references tratamento (id),
    constraint fk_funcionario foreign key (funcionariofk) references funcionario (id)

);


create table tratamento(
    id serial,
    descricao varchar(200),
    constraint pk_tratamento primary key (id)
);


-- inserts para cadastrar valores padrões na combobox

insert into contrato (descricao) values ('CLT');
insert into contrato (descricao) values ('Pessoa Juridica');

insert into cargo (descricao) values ('Recepcionista');
insert into cargo (descricao) values ('Esteticista');
insert into cargo (descricao) values ('Gerente');

-- Views que foram criadas 

create view listafunc as  -- View para dar um select na tabela funcionario e tabelas relacionadas
select f.id as idfuncionario, f.nome, f.cpf, f.dtNascimento, f.sexo, f.logradouro, f.bairro, f.numero, f.cidade,
f.uf, f.cep, f.telFixo, f.celular, f.salario, u.email, u.id as idusuario, t.id as idtipoacesso, t.descricao as descricaotipoacesso,
ct.id as idcontrato, ct.descricao as descricaocontrato, cg.id as idcargo, cg.descricao as descricaocargo 
from funcionario f, usuario u, tipoAcesso t, contrato ct, cargo cg where f.usuarioFK = u.id and 
u.tipoAcessoFK = t.id and f.contratoFK = ct.id and f.cargoFK = cg.id and f.situacao <> 2;

-- inserts para cadastrar tratamentos na combo

insert into tratamento (descricao) values ('limpeza de pele');
insert into tratamento (descricao) values ('Drenagem linfática');
insert into tratamento (descricao) values ('Massagem desportiva');
insert into tratamento (descricao) values ('Endermologia');
insert into tratamento (descricao) values ('Massagem modeladora');

select c.nome, t.descricao from agendamento a, cliente c, tratamento t where a.clientefk = c.id and a.tratamentofk = 
t.id where c.id=?;