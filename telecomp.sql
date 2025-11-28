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

CREATE TABLE Area (
    idArea INT AUTO_INCREMENT PRIMARY KEY,
    nombreArea VARCHAR(50) NOT NULL UNIQUE,
    descripcion VARCHAR(255)
);


CREATE TABLE Usuario (
    idUsuario INT AUTO_INCREMENT PRIMARY KEY,
    nombreUsuario VARCHAR(50) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL, 
    rol VARCHAR(20) NOT NULL,
    idArea INT, 
    FOREIGN KEY (idArea) REFERENCES Area(idArea)
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

INSERT INTO Usuario (nombreUsuario, contrasena, rol, idArea) VALUES 
('admin', 'admin123', 'Admin', 1),
('agente1', 'agente123', 'Agente', 3),
('tecnico1', 'tecnico123', 'Tecnico', 1);


--Procedimientos Almacenados
DELIMITER //

DROP PROCEDURE IF EXISTS sp_validarUsuario //
CREATE PROCEDURE sp_validarUsuario(
    IN p_nombreUsuario VARCHAR(50),
    IN p_contrasena VARCHAR(255)
)
BEGIN
    SELECT idUsuario, nombreUsuario, rol 
    FROM Usuario 
    WHERE nombreUsuario = p_nombreUsuario AND contrasena = p_contrasena;
END //

DROP PROCEDURE IF EXISTS sp_registrarCliente //
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

DROP PROCEDURE IF EXISTS sp_registrarUsuario //
CREATE PROCEDURE sp_registrarUsuario(
    IN p_nombre VARCHAR(50),
    IN p_pass VARCHAR(255),
    IN p_rol VARCHAR(20),
    IN p_idArea INT
)
BEGIN
    INSERT INTO Usuario (nombreUsuario, contrasena, rol, idArea) 
    VALUES (p_nombre, p_pass, p_rol, p_idArea);
END //

DROP PROCEDURE IF EXISTS sp_listarUsuarios //
CREATE PROCEDURE sp_listarUsuarios()
BEGIN
    SELECT 
        u.idUsuario, 
        u.nombreUsuario, 
        u.rol, 
        a.nombreArea 
    FROM Usuario u
    INNER JOIN Area a ON u.idArea = a.idArea;
END //


DROP PROCEDURE IF EXISTS sp_listarAreas //
CREATE PROCEDURE sp_listarAreas()
BEGIN
    SELECT * FROM Area;
END //

DROP PROCEDURE IF EXISTS sp_actualizarUsuario //
CREATE PROCEDURE sp_actualizarUsuario(
    IN p_idUsuario INT,
    IN p_nombre VARCHAR(50),
    IN p_pass VARCHAR(255),
    IN p_rol VARCHAR(20),
    IN p_idArea INT
)
BEGIN
    UPDATE Usuario 
    SET nombreUsuario = p_nombre, 
        contrasena = p_pass, 
        rol = p_rol, 
        idArea = p_idArea
    WHERE idUsuario = p_idUsuario;
END //

DROP PROCEDURE IF EXISTS sp_eliminarUsuario //
CREATE PROCEDURE sp_eliminarUsuario(
    IN p_idUsuario INT
)
BEGIN
    DELETE FROM Usuario WHERE idUsuario = p_idUsuario;
END //

DELIMITER ;