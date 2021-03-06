
drop user buses cascade;

create user buses identified by buses;
grant connect to buses;
grant create session to buses;
grant resource to buses;
grant unlimited tablespace to buses;

connect buses/buses;

CREATE SEQUENCE seq START WITH 1 INCREMENT BY 1;

create table usuarios(
    id number primary key,
    correo varchar2(50) unique not null,
    nombre varchar2(50) not null,
    apellidos varchar2(50) not null,
    contrasena varchar2(256) not null
);

create table empresas(
    id number primary key,
    nombre varchar2(50) not null,
    correo varchar2(50) not null,
    contrasena varchar2(256) not null,
    telefono varchar2(50) not null
);

create table rutas(
    id number primary key,
    empresaId number not null,
    descripcion varchar2(100),
    latitudInicio float not null,
    longitudInicio float not null,
    latitudFinal float not null,
    longitudFinal float not null,
    tiempoEstimado number,
    CONSTRAINT fk_rutas_empresas FOREIGN KEY (empresaId) REFERENCES empresas(id)
);

create table horarios(
    id number primary key,
    rutaId number not null,
    hora varchar2(5) not null,
    CONSTRAINT fk_horarios_rutas FOREIGN KEY (rutaId) REFERENCES rutas(id)
);

create table comentarios(
    id number primary key,
    nombreUsuarioC varchar2(50),
    nombreRuta varchar2(50),
    comentario varchar2(250),
    fecha  varchar2(12)
);

