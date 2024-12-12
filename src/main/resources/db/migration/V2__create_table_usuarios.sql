create table usuarios(
	id bigint not null auto_increment,
	nombre varchar(150) not null,
	correo_electronico varchar(100),
	contrasena varchar(250),
	
	primary key(id)
);