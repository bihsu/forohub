create table cursos(
	id bigint not null auto_increment,
	nombre varchar(150) not null,
	categoria varchar(50),
	
	primary key(id)
);