-- TRIGGER 1: validar que el vehículo esté disponible antes de crear un alquiler
DELIMITER //
CREATE TRIGGER before_alquiler_check BEFORE INSERT ON Alquiler FOR EACH ROW
BEGIN
    DECLARE estado_v VARCHAR(20);
    SELECT estado INTO estado_v FROM Vehiculo WHERE matricula = NEW.matricula;
    IF estado_v IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Vehículo no existe';
    ELSEIF estado_v != 'disponible' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Vehículo no disponible';
    END IF;
END //
DELIMITER ;

-- TRIGGER 2: actualizar el estado del vehículo cuando se crea un alquiler
DELIMITER //
CREATE TRIGGER after_alquiler_insert AFTER INSERT ON Alquiler FOR EACH ROW
BEGIN
    UPDATE Vehiculo
    SET estado = 'alquilado'
    WHERE matricula = NEW.matricula;
END //
DELIMITER ;

-- FUNCIÓN 1: total gastado por un cliente
DELIMITER //
CREATE FUNCTION TotalGastadoCliente(p_dni VARCHAR(9)) RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN
    DECLARE total DECIMAL(10,2);
    SELECT IFNULL(SUM(precio_total), 0) INTO total
    FROM Alquiler
    WHERE id_cliente = p_dni;
    RETURN total;
END //
DELIMITER ;

-- FUNCIÓN 2: número de alquileres de un vehículo
DELIMITER //
CREATE FUNCTION ConteoAlquileresVehiculo(p_matricula VARCHAR(20)) RETURNS INT
DETERMINISTIC
BEGIN
    DECLARE total INT;
    SELECT COUNT(*) INTO total
    FROM Alquiler
    WHERE matricula = p_matricula;
    RETURN total;
END //
DELIMITER ;

-- CREAR TABLA PARA CURSORS Y AVISOS
CREATE TABLE AvisosAlquiler (
    id_aviso INT AUTO_INCREMENT PRIMARY KEY,
    id_alquiler INT,
    mensaje VARCHAR(255),
    fecha_aviso TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- CURSOR 1: alerta de alquileres activos por dias
DELIMITER //
CREATE PROCEDURE AlertarAlquileresLargos()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE v_id INT;
    DECLARE v_fecha DATE;
    DECLARE v_dias INT;
    DECLARE cur CURSOR FOR
        SELECT id_alquiler, fecha_inicio
        FROM Alquiler
        WHERE estado_contrato = 'activo';
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    OPEN cur;
    read_loop: LOOP
        FETCH cur INTO v_id, v_fecha;
        IF done THEN
            LEAVE read_loop;
        END IF;
        SET v_dias = DATEDIFF(CURDATE(), v_fecha);
        IF v_dias > 30 THEN
            INSERT INTO AvisosAlquiler(id_alquiler, mensaje)
            VALUES(v_id, CONCAT('Alquiler activo por ', v_dias, ' días'));
        END IF;
    END LOOP;
    CLOSE cur;
END //
DELIMITER ;

-- CURSOR 2: listar clientes con un alquiler activo
DELIMITER //
CREATE PROCEDURE ListarClientesAlquilerActivo()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE v_dni VARCHAR(9);
    DECLARE v_nombre VARCHAR(100);
    DECLARE cur CURSOR FOR
        SELECT c.dni, c.nombre
        FROM Cliente c
        JOIN Alquiler a ON c.dni = a.id_cliente
        WHERE a.estado_contrato = 'activo'
        GROUP BY c.dni, c.nombre;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    OPEN cur;
    read_loop: LOOP
        FETCH cur INTO v_dni, v_nombre;
        IF done THEN
            LEAVE read_loop;
        END IF;
        INSERT INTO AvisosAlquiler(id_alquiler, mensaje)
        VALUES(0, CONCAT('Cliente activo: ', v_nombre, ' (', v_dni, ')'));
    END LOOP;
    CLOSE cur;
END //
DELIMITER ;

-- PROCEDIMIENTO 1: registrar devolución de un alquiler y actualizar vehículo
DELIMITER //
CREATE PROCEDURE RegistrarDevolucion(
    IN p_id_alquiler INT,
    IN p_fecha_real DATE,
    IN p_precio_final DECIMAL(10,2)
)
BEGIN
    UPDATE Alquiler
    SET fecha_real = p_fecha_real,
        precio_total = p_precio_final,
        estado_contrato = 'finalizado'
    WHERE id_alquiler = p_id_alquiler;

    UPDATE Vehiculo v
    JOIN Alquiler a ON a.matricula = v.matricula
    SET v.estado = 'disponible'
    WHERE a.id_alquiler = p_id_alquiler;
END //
DELIMITER ;

-- PROCEDIMIENTO 2: cambiar categoría y precio base de un vehículo
DELIMITER //
CREATE PROCEDURE CambiarCategoriaVehiculo(
    IN p_matricula VARCHAR(20),
    IN p_nueva_categoria INT,
    IN p_nuevo_precio_base DECIMAL(10,2)
)
BEGIN
    UPDATE Vehiculo
    SET id_categoria = p_nueva_categoria,
        precio_base = p_nuevo_precio_base
    WHERE matricula = p_matricula;
END //
DELIMITER ;
