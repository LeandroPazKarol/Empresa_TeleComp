CREATE DATABASE IF NOT EXISTS telecom_db;
USE telecom_db;
select * from cliente;
CREATE TABLE Cliente (
    idCliente INT AUTO_INCREMENT PRIMARY KEY,
    DNI VARCHAR(8) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
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
    rol VARCHAR(20) NOT NULL ,
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

INSERT INTO Usuario (nombreUsuario, contrasena, rol) VALUES 
('admin', 'admin123', 'Admin'),
('agente1', 'agente123', 'Agente'),
('tecnico1', 'tecnico123', 'Tecnico');

-- Insertar un cliente de ejemplo
INSERT INTO Cliente (DNI, nombres, apellidos, telefono, email, numeroContrato) VALUES
('12445678', 'Juanito', 'Pére', '999799999', 'juan@example.com', 'CNT-02345');
SELECT*FROM CLIENTE;
INSERT INTO Reclamo (
    tipo, descripcion, estado, canalIngreso, idCliente, idAreaAsignada, idUsuarioRegistra
) VALUES (
    'Queja',
    'No tengo señal desde ayer en mi área técnica',
    'Abierto',
    'Teléfono',
    1,  -- idCliente (ej. Juan Pérez)
    1,  -- idAreaAsignada (ej. Técnica)
    2   -- idUsuarioRegistra 
);



UPDATE Reclamo
SET estado = 'Abierto'
WHERE idReclamo = 1;

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

DELIMITER //
CREATE PROCEDURE sp_resolverReclamo(IN p_idReclamo INT)
BEGIN
    UPDATE Reclamo
    SET estado = 'Resuelto'
    WHERE idReclamo = p_idReclamo;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE sp_listarReclamosAbiertos()
BEGIN
    SELECT * FROM Reclamo WHERE estado = 'Abierto';
END //
DELIMITER ;
select*from reclamo;
DELIMITER //
CREATE PROCEDURE sp_listarTodosReclamos()
BEGIN
    SELECT * FROM Reclamo;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE sp_listarReclamosPendientes()
BEGIN
    SELECT * FROM Reclamo WHERE estado IN ('Abierto', 'Resuelto');
END //
DELIMITER ;
DELIMITER //

CREATE PROCEDURE sp_registrarReclamo(
    IN p_tipo VARCHAR(50),
    IN p_descripcion TEXT,
    IN p_estado VARCHAR(20),
    IN p_canal VARCHAR(30),
    IN p_idCliente INT,
    IN p_idArea INT,
    IN p_idUsuario INT
)
BEGIN
    INSERT INTO reclamo(fechaRegistro, tipo, descripcion, estado, canalIngreso, idCliente, idAreaAsignada, idUsuarioRegistra)
    VALUES (NOW(), p_tipo, p_descripcion, p_estado, p_canal, p_idCliente, p_idArea, p_idUsuario);
END //

DELIMITER ;
 
DELIMITER //

CREATE PROCEDURE sp_buscarClientePorDNI(
    IN p_dni VARCHAR(8)
)
BEGIN
    SELECT 
        idCliente,
        DNI,
        nombre,
        apellidos,
        telefono,
        email,
        numeroContrato
    FROM cliente
    WHERE DNI = p_dni;
END //

DELIMITER ;
select * from resolucion;
select * from reclamo;

DELIMITER //
CREATE PROCEDURE sp_listarTodosReclamosConResolucion()
BEGIN
    SELECT 
        r.idReclamo, r.tipo, r.descripcion, r.estado, r.idCliente, r.idAreaAsignada, r.idUsuarioRegistra,
        res.idResolucion, res.fechaResolucion, res.descripcion AS descripcionResolucion, res.responsable
    FROM 
        Reclamo r
    LEFT JOIN 
        Resolucion res ON r.idReclamo = res.idReclamo;
END
//DELIMITER ;
DELIMITER $$

CREATE PROCEDURE sp_actualizarResolucion(
    IN p_idReclamo INT,
    IN p_descripcion TEXT,
    IN p_responsable VARCHAR(100)
)
BEGIN
    UPDATE Resolucion
    SET descripcion = p_descripcion,
        responsable = p_responsable,
        fechaResolucion = CURDATE()
    WHERE idReclamo = p_idReclamo;
END
$$

DELIMITER ;

DELIMITER //
CREATE PROCEDURE sp_actualizarCliente(
    IN p_DNI VARCHAR(8),
    IN p_nombre VARCHAR(100),
    IN p_apellidos VARCHAR(100),
    IN p_telefono VARCHAR(15),
    IN p_email VARCHAR(100),
    IN p_numeroContrato VARCHAR(20)
)
BEGIN
    UPDATE Cliente
    SET nombre = p_nombre,
        apellidos = p_apellidos,
        telefono = p_telefono,
        email = p_email,
        numeroContrato = p_numeroContrato
    WHERE DNI = p_DNI;
END //
DELIMITER //
CREATE PROCEDURE sp_eliminarCliente(
    IN p_DNI VARCHAR(8)
)
BEGIN
    DELETE FROM Cliente WHERE DNI = p_DNI;
END //

DELIMITER //
DROP PROCEDURE IF EXISTS sp_listarClientes //
CREATE PROCEDURE sp_listarClientes()
BEGIN
    SELECT * FROM Cliente;
END //

DELIMITER ;
DELIMITER //
CREATE PROCEDURE sp_registrarCliente(
    IN p_DNI VARCHAR(8),
    IN p_nombre VARCHAR(100),
    IN p_apellidos VARCHAR(100),
    IN p_telefono VARCHAR(15),
    IN p_email VARCHAR(100),
    IN p_numeroContrato VARCHAR(20)
)
BEGIN
    INSERT INTO Cliente (DNI, nombre, apellidos, telefono, email, numeroContrato)
    VALUES (p_DNI, p_nombre, p_apellidos, p_telefono, p_email, p_numeroContrato);
END 
//

DROP PROCEDURE IF EXISTS sp_registrarUsuario 
DELIMITER //
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

DROP PROCEDURE IF EXISTS sp_listarUsuarios
DELIMITER //
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

DELIMITER //
DROP PROCEDURE IF EXISTS sp_listarAreas 
DELIMITER //
CREATE PROCEDURE sp_listarAreas()
BEGIN
    SELECT * FROM Area;
END //

DROP PROCEDURE IF EXISTS sp_actualizarUsuario 
DELIMITER //
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

DROP PROCEDURE IF EXISTS sp_eliminarUsuario
 DELIMITER //
CREATE PROCEDURE sp_eliminarUsuario(
    IN p_idUsuario INT
)
BEGIN
    DELETE FROM Usuario WHERE idUsuario = p_idUsuario;
END //

DELIMITER ;


DELIMITER $$
CREATE DEFINER=root@localhost PROCEDURE sp_consultarRegistros()
select * from reclamo$$
DELIMITER ;


DELIMITER $$
CREATE DEFINER=root@localhost PROCEDURE sp_porcentaje_primer_contacto()
BEGIN
    SELECT 
        CASE 
            WHEN total = 0 THEN 0
            ELSE ROUND((primer_contacto / total) * 100, 2)
        END AS porcentaje
    FROM (
        SELECT
            COUNT(*) AS total,
            SUM(CASE 
                    WHEN DATE(r.fechaRegistro) = DATE(res.fechaResolucion)
                    THEN 1 ELSE 0 
                END
            ) AS primer_contacto
        FROM Reclamo r
        INNER JOIN Resolucion res ON r.idReclamo = res.idReclamo
    ) AS t;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=root@localhost PROCEDURE sp_promedio_tiempo_resolucion()
BEGIN
    SELECT AVG(horas) AS horasPromedio FROM (
        SELECT 
            r.idReclamo,
            TIMESTAMPDIFF(HOUR, r.fechaRegistro, MIN(res.fechaResolucion)) AS horas
        FROM Reclamo r
        JOIN Resolucion res ON res.idReclamo = r.idReclamo
        GROUP BY r.idReclamo, r.fechaRegistro
        HAVING MIN(res.fechaResolucion) IS NOT NULL
    ) AS t;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=root@localhost PROCEDURE sp_reporte_detalle(
    IN p_fechaDesde DATETIME,
    IN p_fechaHasta DATETIME
)
BEGIN
    SELECT
        r.idReclamo,
        r.fechaRegistro,
        r.tipo,
        r.estado,
        MIN(res.fechaResolucion) AS fechaResolucion,
        CASE WHEN MIN(res.fechaResolucion) IS NULL THEN NULL
             ELSE TIMESTAMPDIFF(HOUR, r.fechaRegistro, MIN(res.fechaResolucion))
        END AS tiempoResolucionHoras,
        CASE WHEN (
            SELECT COUNT(*) FROM Resolucion rr WHERE rr.idReclamo = r.idReclamo
        ) = 1 THEN 1 ELSE 0 END AS esPrimerContacto
    FROM Reclamo r
    LEFT JOIN Resolucion res ON res.idReclamo = r.idReclamo
    WHERE (p_fechaDesde IS NULL OR r.fechaRegistro >= p_fechaDesde)
      AND (p_fechaHasta IS NULL OR r.fechaRegistro <= p_fechaHasta)
    GROUP BY r.idReclamo, r.fechaRegistro, r.tipo, r.estado;
END$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=root@localhost PROCEDURE sp_total_reclamos()
BEGIN
    SELECT COUNT(*) AS total FROM Reclamo;
END$$
DELIMITER ;
DELIMITER $$

CREATE PROCEDURE sp_guardar_encuesta(
    IN p_idCliente INT,
    IN p_idReclamo INT,
    IN p_fechaEnvio DATE
)
BEGIN
    INSERT INTO EncuestaSatisfaccion (idCliente, idReclamo, fechaEnvio)
    VALUES (p_idCliente, p_idReclamo, p_fechaEnvio);
END $$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE sp_guardar_notificacion(
    IN p_idReclamo INT,
    IN p_medio VARCHAR(50),
    IN p_contenido TEXT,
    IN p_fechaEnvio DATETIME
)
BEGIN
    INSERT INTO Notificacion (idReclamo, medio, contenido, fechaEnvio)
    VALUES (p_idReclamo, p_medio, p_contenido, p_fechaEnvio);
END $$

DELIMITER ;

DELIMITER //
CREATE PROCEDURE sp_resolverReclamoConResolucion(
    IN p_idReclamo INT,
    IN p_fechaResolucion DATE,
    IN p_descripcion TEXT,
    IN p_responsable VARCHAR(100)
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;
    
    START TRANSACTION;
    
    -- Actualizar ESTADO a 'resuelto'
    UPDATE Reclamo SET estado = 'Resuelto' WHERE idReclamo = p_idReclamo;
    
    -- Guardar resolución
    INSERT INTO Resolucion (idReclamo, fechaResolucion, descripcion, responsable)
    VALUES (p_idReclamo, p_fechaResolucion, p_descripcion, p_responsable);
    
    COMMIT;
END //
DELIMITER ;
DELIMITER $$
CREATE PROCEDURE sp_actualizarReclamo(
    IN p_idReclamo INT,
    IN p_tipo VARCHAR(50),
    IN p_descripcion TEXT,
    IN p_estado VARCHAR(20),
    IN p_canal VARCHAR(30),
    IN p_idCliente INT,
    IN p_idArea INT,
    IN p_idUsuario INT
)
BEGIN
    UPDATE reclamo
    SET 
        tipo = p_tipo,
        descripcion = p_descripcion,
        estado = p_estado,
        canalIngreso = p_canal,
        idCliente = p_idCliente,
        idAreaAsignada = p_idArea,
        idUsuarioRegistra = p_idUsuario
    WHERE idReclamo = p_idReclamo;
END $$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE sp_eliminarReclamo(
    IN p_idReclamo INT
)
BEGIN
    DELETE FROM reclamo
    WHERE idReclamo = p_idReclamo;
END $$

DELIMITER ;
select * from cliente;
select * from usuario;
select * from reclamo;

select*from notificacion;
select*from encuestasatisfaccion;



