create table IF NOT EXISTS odontologos(id int auto_increment primary key,matricula varchar(255),nombre varchar(255), apellido varchar (255));

create table IF NOT EXISTS domicilios(id int auto_increment primary key,calle varchar(255),numero varchar (255),localidad varchar (255),provincia varchar (255));

create table IF NOT EXISTS pacientes(id int auto_increment primary key,nombre varchar(255),apellido varchar (255),dni varchar (255),fecha_ingreso TIMESTAMP WITHOUT TIME ZONE,domicilio_id int);

create table IF NOT EXISTS turnos(id int auto_increment primary key, paciente_id int, odontologo_id int, fecha TIMESTAMP WITHOUT TIME ZONE);