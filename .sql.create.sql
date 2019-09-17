create table agendamento (id integer not null, extra_agua bit, extra_biscoito bit, extra_cafe bit, id_responsavel integer, id_solicitante integer, publico_externo bit, publico_interno bit, quantidade_audiovisual integer, quantidade_equipamento integer, quantidade_impressora integer, quantidade_pessoas integer, tema_reuniao varchar(255), primary key (id)) engine=MyISAM
create table agendamento_seq (next_val bigint) engine=MyISAM
insert into agendamento_seq values ( 1 )
create table data_reuniao (id integer not null, data datetime, id_sala integer, id_agendamento integer, primary key (id)) engine=MyISAM
create table data_reuniao_seq (next_val bigint) engine=MyISAM
insert into data_reuniao_seq values ( 1 )
create table hora_reuniao (id integer not null, hora integer, id_data_reuniao integer, primary key (id)) engine=MyISAM
create table hora_reuniao_seq (next_val bigint) engine=MyISAM
insert into hora_reuniao_seq values ( 1 )
alter table data_reuniao add constraint FKl9kbw1e200wnsaljcar3ivlt3 foreign key (id_agendamento) references agendamento (id)
alter table hora_reuniao add constraint FKax82om2vij2s23x5attqtpcj2 foreign key (id_data_reuniao) references data_reuniao (id)
