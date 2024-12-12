create table topicos(
	id bigint not null auto_increment,
	titulo varchar(150) not null,
	mensaje varchar(250) not null,
	fecha_creacion datetime not null,
	estatus tinyint not null,
	autor_id bigint not null,
	curso_id bigint not null,
	
	primary key(id),
	foreign key(autor_id) references usuarios(id),
	foreign key(curso_id) references cursos(id)
);