drop table if exists usr_usuario cascade;
create table usr_usuario (
  usr_id bigint generated always as identity,
  usr_nome varchar(20) not null,
  usr_senha varchar(150) not null,
  primary key (usr_id),
  unique (usr_nome)
);

drop table if exists aut_autorizacao cascade;
create table aut_autorizacao (
  aut_id bigint generated always as identity,
  aut_nome varchar(20) not null,
  primary key (aut_id),
  unique (aut_nome)
);

drop table if exists uau_usuario_autorizacao cascade;
create table uau_usuario_autorizacao (
  usr_id bigint not null,
  aut_id bigint not null,
  primary key (usr_id, aut_id),
  foreign key (usr_id) references usr_usuario (usr_id) on delete restrict on update cascade,
  foreign key (aut_id) references aut_autorizacao (aut_id) on delete restrict on update cascade
);

drop table if exists ant_anotacao cascade;
create table ant_anotacao (
  ant_id bigint generated always as identity,
  ant_texto varchar(256) not null,
  ant_data_hora timestamp not null,
  ant_usr_id bigint not null,
  primary key (ant_id),
  foreign key (ant_usr_id) references usr_usuario(usr_id),
  unique(ant_data_hora, ant_usr_id)
);

drop table if exists tra_trabalho cascade;
create table tra_trabalho (
  tra_id bigint generated always as identity,
  tra_titulo varchar(100) not null unique,
  tra_data_hora_entrega timestamp,
  tra_descricao varchar(200),
  tra_grupo varchar(20) not null,
  tra_nota int,
  tra_justificativa varchar(100),
  primary key (tra_id),
  unique(tra_titulo,tra_grupo)
);

drop table if exists rev_revisao cascade;
create table rev_revisao (
  rev_id bigint generated always as identity,
  rev_conteudo varchar(100) not null,
  rev_data_hora timestamp not null,
  rev_tipo varchar(10),
  rev_tra_id bigint not null,
  foreign key (rev_tra_id) references tra_trabalho(tra_id),
  unique(rev_data_hora, rev_tra_id),
  primary key (rev_id)
);

drop table if exists con_conteudo cascade;
create table con_conteudo (
  con_id bigint generated always as identity,
  con_texto varchar(200) not null,
  con_data_hora_criacao timestamp not null,
  con_data_hora_publicacao timestamp,
  con_tra_id bigint not null,
  foreign key (con_tra_id) references tra_trabalho(tra_id),
  unique(con_texto, con_tra_id),
  primary key (con_id)
);

drop table if exists ane_anexo cascade;
create table ane_anexo (
  ane_id bigint generated always as identity,
  ane_texto varchar(200) not null,
  ane_tipo varchar(10),
  ane_data_hora timestamp not null,
  ane_tra_id bigint not null,
  foreign key (ane_tra_id) references tra_trabalho(tra_id),
  unique(ane_texto, ane_tra_id),
  primary key (ane_id)
);

drop table if exists har_hardware cascade;
create table har_hardware (
  har_id bigint generated always as identity,
  har_descricao varchar(100) not null,
  har_data_hora_anuncio timestamp not null,
  har_data_hora_lancamento timestamp,
  primary key (har_id),
  unique(har_descricao)
);

drop table if exists wis_wishlist cascade;
create table wis_wishlist (
  wis_id bigint generated always as identity,
  wis_comentario varchar(100),
  wis_data_hora_inclusao timestamp not null,
  wis_nivel_interesse int not null,
  wis_har_id bigint not null,
  foreign key (wis_har_id) references har_hardware(har_id),
  unique(wis_data_hora_inclusao, wis_har_id),
  primary key (wis_id)
);

drop table if exists cot_cotacao cascade;
create table cot_cotacao (
  cot_id bigint generated always as identity,
  cot_data_hora timestamp not null,
  cot_valor float not null,
  cot_precaucoes varchar(100),
  cot_har_id bigint not null,
  foreign key (cot_har_id) references har_hardware(har_id),
  unique(cot_data_hora, cot_har_id),
  primary key (cot_id)
);

insert into usr_usuario (usr_nome, usr_senha) 
  values ('admin', '$2a$10$i3.Z8Yv1Fwl0I5SNjdCGkOTRGQjGvHjh/gMZhdc3e7LIovAklqM6C');
insert into aut_autorizacao (aut_nome) 
  values ('ROLE_ADMIN');
insert into uau_usuario_autorizacao (usr_id, aut_id) 
  values (1, 1);
insert into ant_anotacao(ant_texto, ant_data_hora, ant_usr_id) 
  values('Meu novo projeto', '2023-08-01 19:10', 1);
insert into tra_trabalho (tra_titulo, tra_data_hora_entrega, tra_grupo, tra_nota, tra_justificativa) 
  values ('Teste 1', '2025-05-10 10:00', 'Alpha', 6, 'Bom, mas falta conteúdo'),
         ('Teste 2', null, 'Beta', null, 'Incompleto');
insert into rev_revisao (rev_conteudo, rev_data_hora, rev_tipo, rev_tra_id)
  values ('Revisão inicial do Teste 1', '2025-05-10 11:20', null, 1),
         ('Revisão final do Teste 1', '2025-05-12 17:00', 'FINAL', 1);
insert into con_conteudo (con_texto, con_data_hora_criacao, con_data_hora_publicacao, con_tra_id)
  values ('Conteúdo do Teste 1', '2025-05-10 10:02', '2025-05-10 10:30', 1),
         ('Conteúdo do Teste 2', '2025-06-01 17:02', null, 2);
insert into ane_anexo (ane_texto, ane_tipo, ane_data_hora, ane_tra_id)
  values ('C corresponde à velocidade da luz', 'Glossário', '2025-05-10 10:05', 1),
         ('O mar não é um bolachão', null, '2025-06-01 17:05', 2);
insert into har_hardware (har_descricao, har_data_hora_anuncio, har_data_hora_lancamento)
  values ('Novo Smartphone', '2025-01-01 09:00', '2025-02-01 10:00'),
         ('Laptop Gamer', '2025-03-01 11:00', null);
insert into wis_wishlist (wis_comentario, wis_data_hora_inclusao, wis_nivel_interesse, wis_har_id)
  values ('Gostaria muito de ter esse smartphone', '2025-01-02 10:00', 5, 1),
         ('Preciso de um laptop novo', '2025-03-02 12:00', 4, 2);
insert into cot_cotacao (cot_data_hora, cot_valor, cot_precaucoes, cot_har_id)
  values ('2025-01-03 10:00', 2999.99, 'Garantia de 2 anos', 1),
         ('2025-03-03 12:00', 4999.99, 'Inclui acessórios', 2);

--Comente essa linha se o usuario ja existir
create user spring with password 'pass123';

--Permite que o usuario spring possa acessar o banco de dados
grant update, delete, insert, select on all tables in schema public to spring;