
CREATE DATABASE IF NOT EXISTS telecom_db;
USE telecom_db;

CREATE TABLE Cliente (
    idCliente INT AUTO_INCREMENT PRIMARY KEY,
    DNI VARCHAR(8) NOT NULL UNIQUE,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    telefono VARCHAR(15),
    email VARCHAR(100),
    numeroContrato VARCHAR(20) UNIQUE
);

CREATE TABLE Usuario (
    idUsuario INT AUTO_INCREMENT PRIMARY KEY,
    nombreUsuario VARCHAR(50) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL, 
    rol VARCHAR(20) NOT NULL 
);

CREATE TABLE Area (
    idArea INT AUTO_INCREMENT PRIMARY KEY,
    nombreArea VARCHAR(50) NOT NULL UNIQUE,
    descripcion VARCHAR(255)
);

CREATE TABLE Reclamo (
    idReclamo INT AUTO_INCREMENT PRIMARY KEY,
    fechaRegistro DATETIME DEFAULT CURRENT_TIMESTAMP,
    tipo VARCHAR(50) NOT NULL, 
    descripcion TEXT NOT NULL,
    estado VARCHAR(20) NOT NULL, 
    canalIngreso VARCHAR(30), 
    idCliente INT,
    idAreaAsignada INT,
    idUsuarioRegistra INT,
    FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente),
    FOREIGN KEY (idAreaAsignada) REFERENCES Area(idArea),
    FOREIGN KEY (idUsuarioRegistra) REFERENCES Usuario(idUsuario)
);

CREATE TABLE Resolucion (
    idResolucion INT AUTO_INCREMENT PRIMARY KEY,
    fechaResolucion DATETIME DEFAULT CURRENT_TIMESTAMP,
    descripcion TEXT NOT NULL,
    responsable VARCHAR(100), 
    idReclamo INT,
    FOREIGN KEY (idReclamo) REFERENCES Reclamo(idReclamo)
);

CREATE TABLE Notificacion (
    idNotificacion INT AUTO_INCREMENT PRIMARY KEY,
    fechaEnvio DATETIME DEFAULT CURRENT_TIMESTAMP,
    medio VARCHAR(10), 
    contenido VARCHAR(255),
    idReclamo INT,
    FOREIGN KEY (idReclamo) REFERENCES Reclamo(idReclamo)
);

CREATE TABLE EncuestaSatisfaccion (
    idEncuesta INT AUTO_INCREMENT PRIMARY KEY,
    fechaEnvio DATETIME,
    puntaje INT,
    comentarios TEXT,
    idCliente INT,
    idReclamo INT,
    FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente),
    FOREIGN KEY (idReclamo) REFERENCES Reclamo(idReclamo)
);

INSERT INTO Area (nombreArea, descripcion) VALUES 
('Técnica', 'Resuelve problemas de señal e infraestructura.'),
('Facturación', 'Resuelve problemas de cobros y planes.'),
('Servicio al Cliente', 'Atiende consultas generales y bajas.');

INSERT INTO Usuario (nombreUsuario, contrasena, rol) VALUES 
('admin', 'admin123', 'Admin'),
('agente1', 'agente123', 'Agente'),
('tecnico1', 'tecnico123', 'Tecnico');


DELIMITER //

CREATE PROCEDURE sp_validarUsuario(
    IN p_nombreUsuario VARCHAR(50),
    IN p_contrasena VARCHAR(255)
)
BEGIN
    SELECT idUsuario, nombreUsuario, rol 
    FROM Usuario 
    WHERE nombreUsuario = p_nombreUsuario AND contrasena = p_contrasena;
END //

CREATE PROCEDURE sp_registrarCliente(
    IN p_DNI VARCHAR(8),
    IN p_nombres VARCHAR(100),
    IN p_apellidos VARCHAR(100),
    IN p_telefono VARCHAR(15),
    IN p_email VARCHAR(100),
    IN p_numeroContrato VARCHAR(20)
)
BEGIN
    INSERT INTO Cliente (DNI, nombres, apellidos, telefono, email, numeroContrato)
    VALUES (p_DNI, p_nombres, p_apellidos, p_telefono, p_email, p_numeroContrato);
END //

DROP PROCEDURE IF EXISTS sp_actualizarCliente //
CREATE PROCEDURE sp_actualizarCliente(
    IN p_DNI VARCHAR(8),
    IN p_nombres VARCHAR(100),
    IN p_apellidos VARCHAR(100),
    IN p_telefono VARCHAR(15),
    IN p_email VARCHAR(100),
    IN p_numeroContrato VARCHAR(20)
)
BEGIN
    UPDATE Cliente
    SET nombres = p_nombres,
        apellidos = p_apellidos,
        telefono = p_telefono,
        email = p_email,
        numeroContrato = p_numeroContrato
    WHERE DNI = p_DNI;
END //

DROP PROCEDURE IF EXISTS sp_eliminarCliente //
CREATE PROCEDURE sp_eliminarCliente(
    IN p_DNI VARCHAR(8)
)
BEGIN
    DELETE FROM Cliente WHERE DNI = p_DNI;
END //

DROP PROCEDURE IF EXISTS sp_buscarClientePorDNI //
CREATE PROCEDURE sp_buscarClientePorDNI(
    IN p_DNI VARCHAR(8)
)
BEGIN
    SELECT * FROM Cliente WHERE DNI = p_DNI;
END //

DROP PROCEDURE IF EXISTS sp_listarClientes //
CREATE PROCEDURE sp_listarClientes()
BEGIN
    SELECT * FROM Cliente;
END //

DELIMITER ;
