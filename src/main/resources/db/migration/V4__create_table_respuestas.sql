create table respuestas(
	id bigint not null auto_increment,
	mensaje varchar(250) not null,
	fecha_creacion datetime not null,
	topico_id bigint not null,
	usuario_id bigint not null,
	solucion varchar(250) not null,
	
	primary key(id),
	
	foreign key(topico_id) references topicos(id),
	foreign key(usuario_id) references usuarios(id)
);