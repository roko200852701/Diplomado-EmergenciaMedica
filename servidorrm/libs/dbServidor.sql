create database bdServidor;
use bdServidor;


create table Especialidad(
	id integer not null,
    nombre varchar(50) not null,
    PRIMARY KEY (id)    
);

create table Persona(
		id integer not null auto_increment,
    	nombre varchar(80) not null,
    	matricula varchar(50) not null,
		ci varchar(50) not null,
		telefono varchar(20),
		tipo INTEGER not null,
		idEspecialidad integer,
    	primary key (id),
		foreign key (idEspecialidad) references Especialidad(id)
		on delete cascade
                on update cascade
);

create table Institucion(
	id integer not null auto_increment,
    nombre varchar(50) not null,
    direccion varchar(50) not null,
    telefono varchar(20) not null,
    latitud double not null,
    longitud double not null,
    primary key (id)
);

create table Ambulancia(
	id integer not null auto_increment,
    idMedico integer not null,
    idInstitucion integer not null,
    estado varchar(1) not null,
    primary key (id),
    foreign key (idMedico) references Persona(id),
    foreign key (idInstitucion) references Institucion(id)
    on delete cascade
    on update cascade
);


create table Emergencia(
    id integer not null auto_increment,
    idPaciente integer not null,
    idAmbulancia integer not null,
    fecha datetime not null,
    estador varchar(1),
    primary key (id),
    foreign key (idPaciente) references Persona(id),
    foreign key (idAmbulancia) references Ambulancia(id)    
    on delete cascade
    on update cascade
);

create table HorarioAtencion(
    id integer not null,
    idMedico integer not null,
    lunes TINYINT not null,
    martes TINYINT not null,
    miercoles TINYINT not null,
    jueves TINYINT not null,
    viernes TINYINT not null,
    sabado TINYINT not null,
    domingo TINYINT not null,
    horaInicio time not null,
    horaFin time not null,
    primary key (id),
    foreign key (idMedico) references Persona(id)
    on delete cascade
    on update cascade
);

create table Puntuacion(
    id integer not null,
    idMedico integer not null,
    calificacion double,
    comentario varchar(200),
	primary key (id),
	foreign key (idMedico) references Persona(id)
    on delete cascade
    on update cascade
);



