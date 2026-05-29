-- PROCEDIMIENTO obtener_alquileres_por_estado
-- Permite al gerente consultar rápidamente todos los alquileres según su estado (Activo, Completado o Cancelado). 

DELIMITER //
CREATE PROCEDURE obtener_alquileres_por_estado(IN p_estado VARCHAR(20))
BEGIN
    -- Si es válido, vacía la tabla auxiliar y la rellena con los alquileres filtrados.
    IF p_estado NOT IN ('Activo','Completado','Cancelado') THEN
        SELECT 'El estado solo puede ser: Activo, Completado o Cancelado' AS mensaje;
-- Si el estado no es válido, muestra un mensaje de error.
    ELSE
        TRUNCATE TABLE estados_alquileres;
        INSERT INTO estados_alquileres(id_alquiler, fecha_inicio, fecha_devolucion_prevista, fecha_devolucion_real, estado_contrato, dni_cliente)
        SELECT id_alquiler, fecha_inicio, fecha_devolucion_prevista, fecha_devolucion_real, estado_contrato, id_cliente FROM Alquiler WHERE estado_contrato = p_estado;
    END IF;
END //
DELIMITER ;

-- PROCEDIMIENTO calcular_alquileres_por_mes
-- Genera un resumen mensual de alquileres para un año concreto.

DELIMITER //
CREATE PROCEDURE calcular_alquileres_por_mes(IN p_anno INT)
BEGIN
    DECLARE v_mes INT;
    TRUNCATE TABLE resumen_alquileres_mes;
-- Si el año no está en rango, devuelve un mensaje de error.
    IF p_anno < 2000 OR p_anno > 2100 THEN
        SELECT 'Año fuera de rango (2000-2100)' AS mensaje;
    ELSE
        SET v_mes = 1;
-- Recorre los 12 meses con un WHILE e inserta los totales en una tabla auxiliar.
        WHILE v_mes <= 12 DO
            INSERT INTO resumen_alquileres_mes(anno, mes, num_alquileres)
            SELECT p_anno, v_mes, COUNT(*) FROM Alquiler  WHERE YEAR(fecha_inicio) = p_anno AND MONTH(fecha_inicio) = v_mes;
            SET v_mes = v_mes + 1;
        END WHILE;
    END IF;
END //
DELIMITER ;

-- FUNCIÓN mes_en_letras
-- Convierte un número de mes (1-12) en su nombre en español.
DELIMITER //
CREATE FUNCTION mes_en_letras(mes INT) RETURNS VARCHAR(10)
BEGIN
    IF mes < 1 OR mes > 12 THEN RETURN 'Error'; END IF;
    CASE mes
        WHEN 1 THEN RETURN 'Enero';
        WHEN 2 THEN RETURN 'Febrero';
        WHEN 3 THEN RETURN 'Marzo';
        WHEN 4 THEN RETURN 'Abril';
        WHEN 5 THEN RETURN 'Mayo';
        WHEN 6 THEN RETURN 'Junio';
        WHEN 7 THEN RETURN 'Julio';
        WHEN 8 THEN RETURN 'Agosto';
        WHEN 9 THEN RETURN 'Septiembre';
        WHEN 10 THEN RETURN 'Octubre';
        WHEN 11 THEN RETURN 'Noviembre';
        WHEN 12 THEN RETURN 'Diciembre';
    END CASE;
END //
DELIMITER ;

-- FUNCIÓN esBisiesto
DELIMITER //
CREATE FUNCTION esBisiesto(anno INT)
RETURNS BOOLEAN
BEGIN
    DECLARE b BOOLEAN DEFAULT FALSE;
    IF anno <= 0 THEN RETURN FALSE;
    END IF;
    IF MOD(anno,4)=0 THEN
        SET b = TRUE;
        IF MOD(anno,100)=0 THEN SET b = FALSE;
            IF MOD(anno,400)=0 THEN SET b = TRUE;
            END IF;
        END IF;
    END IF;
    RETURN b;
END //
DELIMITER ;

-- FUNCIÓN estado_alquiler
-- Basado en las fechas del contrato.

DELIMITER //
CREATE FUNCTION estado_alquiler(p_id INT)
RETURNS VARCHAR(128)
BEGIN
    DECLARE f_prev DATE;
    DECLARE f_real DATE;
    DECLARE existe INT;
    SELECT COUNT(*) INTO existe FROM Alquiler WHERE id_alquiler = p_id;
-- Devuelve un mensaje indicando si el alquiler no existe. 
    IF existe = 0 THEN RETURN 'El alquiler no existe'; END IF;
SELECT fecha_devolucion_prevista, fecha_devolucion_real INTO f_prev, f_real FROM Alquiler WHERE id_alquiler = p_id;
-- Devuelve un mensaje indicando si el alquiler no ha sido devuelto
    IF f_real IS NULL THEN RETURN 'Alquiler aún no devuelto'; END IF;
--  Devuelve un mensaje indicando si el alquiler fue devuelto con retraso, puntualidad o adelanto
    IF f_real > f_prev THEN RETURN CONCAT('Devuelto con ', DATEDIFF(f_real,f_prev), ' días de retraso');
    ELSEIF f_real = f_prev THEN RETURN 'Devuelto en la fecha prevista';
    ELSE 
        RETURN CONCAT('Devuelto con ', DATEDIFF(f_prev,f_real), ' días de adelanto');
    END IF;
END //
DELIMITER ;

-- FUNCIÓN numero_alquileres
DELIMITER //
CREATE FUNCTION numero_alquileres(mm INT, aaaa INT)
RETURNS INT
BEGIN
    DECLARE total INT;
-- Devuelve el número de alquileres realizados en un mes y año concretos.
    IF mm NOT BETWEEN 1 AND 12 OR aaaa NOT BETWEEN 2000 AND 2100 THEN
        RETURN -1;
-- Si los parámetros no son válidos, devuelve -1.
    END IF;

    SELECT COUNT(*) INTO total FROM Alquiler WHERE MONTH(fecha_inicio)=mm AND YEAR(fecha_inicio)=aaaa;
    RETURN total;
END //
DELIMITER ;

-- TRIGGER
DELIMITER //
CREATE TRIGGER tr_alquiler_insert_actualiza_vehiculo
AFTER INSERT ON Alquiler
FOR EACH ROW
BEGIN
    -- Cuando se crea un alquiler, el vehículo pasa automáticamente a estado 'alquilado'.
    UPDATE Vehiculo
    SET estado = 'alquilado'
    WHERE matricula = NEW.matricula;
END //
DELIMITER ;

-- TRIGGER tr_alquiler_update_estado
DELIMITER //
CREATE TRIGGER tr_alquiler_update_estado
BEFORE UPDATE ON Alquiler
FOR EACH ROW
BEGIN
    -- Si se registra una fecha de devolución real, el contrato pasa a 'Completado' y el vehículo vuelve a estar 'disponible'.
    IF NEW.fecha_devolucion_real IS NOT NULL
    AND (OLD.fecha_devolucion_real IS NULL
    OR OLD.fecha_devolucion_real <> NEW.fecha_devolucion_real) THEN
        SET NEW.estado_contrato = 'Completado';
        UPDATE Vehiculo  SET estado = 'disponible'  WHERE matricula = NEW.matricula;
    END IF;
END //
DELIMITER ;

-- CURSOR listar_vehiculos_alquilados
DELIMITER //
CREATE PROCEDURE listar_vehiculos_alquilados()
BEGIN
    DECLARE v_matricula VARCHAR(10);
    DECLARE v_marca VARCHAR(50);
    DECLARE v_modelo VARCHAR(50);
    DECLARE v_cliente VARCHAR(100);
    DECLARE fin_cursor INT DEFAULT 0;
    DECLARE cur_vehiculos CURSOR FOR
        SELECT v.matricula, v.marca, v.modelo, c.nombre FROM Vehiculo v JOIN Alquiler a ON v.matricula = a.matricula JOIN Cliente c ON a.id_cliente = c.dni WHERE a.estado_contrato = 'Activo';
-- Recorre todos los vehículos actualmente alquilados (estado Activo) y muestra su matrícula, marca, modelo y el cliente que lo tiene.
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin_cursor = 1;
    OPEN cur_vehiculos;
    bucle: LOOP
        FETCH cur_vehiculos INTO v_matricula, v_marca, v_modelo, v_cliente;
        IF fin_cursor = 1 THEN LEAVE bucle; END IF;

        SELECT CONCAT('Vehículo alquilado: ', v_matricula, ' - ', v_marca, ' ', v_modelo,' | Cliente: ', v_cliente) AS Informacion;
    END LOOP bucle;
    CLOSE cur_vehiculos;
END //
DELIMITER ;
