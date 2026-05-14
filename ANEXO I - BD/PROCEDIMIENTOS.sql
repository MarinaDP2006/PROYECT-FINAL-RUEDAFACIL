-- PROCEDIMIENTO obtener_estado_pedidos
DELIMITER //
CREATE PROCEDURE obtener_estado_pedidos(IN p_estado VARCHAR(20))
BEGIN
    IF p_estado NOT IN ('Entregado','Pendiente','Rechazado') THEN
    SELECT 'El estado solo puede ser: Entregado, Pendiente o Rechazado';
    ELSE  TRUNCATE TABLE estados_pedidos;
        INSERT INTO estados_pedidos(codigo_pedido, fecha_pedido, estado, codigo_cliente)
        SELECT codigo_pedido, fecha_pedido, estado, codigo_cliente FROM pedido WHERE estado = p_estado;
    END IF;
END //
DELIMITER ;

-- PROCEDIMIENTO calcular_pares_impares
DELIMITER //
CREATE PROCEDURE calcular_pares_impares(IN tope INT)
BEGIN
    DECLARE numero INT;
    TRUNCATE TABLE pares;
    TRUNCATE TABLE impares;
    IF tope <= 0 THEN
        SELECT 'Entrada negativa o cero';
    ELSE
        SET numero = tope;
        WHILE numero > 0 DO
            IF MOD(numero,2) = 0 THEN
                INSERT INTO pares VALUES(numero);
            ELSE
                INSERT INTO impares VALUES(numero);
            END IF;
            SET numero = numero - 1;
        END WHILE;
    END IF;
END //
DELIMITER ;

-- FUNCIÓN mes_en_letras
DELIMITER //
CREATE FUNCTION mes_en_letras(mes INT) RETURNS VARCHAR(10)
BEGIN
    IF mes < 1 OR mes > 12 THEN RETURN 'Error en parametro';
    END IF;
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

-- FUNCIÓN estado_pedido
DELIMITER //
CREATE FUNCTION estado_pedido(p_id INT)
RETURNS VARCHAR(128)
BEGIN
    DECLARE fp DATE;
    DECLARE fen DATE;
    DECLARE fes DATE;
    DECLARE existe INT;
    SELECT COUNT(*) INTO existe FROM pedido WHERE codigo_pedido = p_id;
    IF existe = 0 THEN RETURN 'El pedido no existe';
    END IF;
    SELECT fecha_pedido, fecha_entrega, fecha_esperada  INTO fp, fen, fes FROM pedido WHERE codigo_pedido = p_id;
    IF fen IS NULL THEN RETURN 'Pedido no entregado';
    END IF;
    IF fen > fes THEN RETURN CONCAT('Entregada con ', DATEDIFF(fen,fes), ' días de retraso');
    ELSEIF fen = fes THEN RETURN 'Entregada con puntualidad';
    ELSE RETURN CONCAT('Entregada con ', DATEDIFF(fes,fen), ' días de adelanto');
    END IF;
END //
DELIMITER ;

-- FUNCIÓN numero_pedidos
DELIMITER //
CREATE FUNCTION numero_pedidos(mm INT, aaaa INT)
RETURNS INT
BEGIN
    DECLARE total INT;
    IF mm NOT BETWEEN 1 AND 12 OR aaaa NOT BETWEEN 1900 AND 2099 THEN RETURN -1;
    END IF;
    SELECT COUNT(*) INTO total FROM pedido WHERE MONTH(fecha_pedido)=mm AND YEAR(fecha_pedido)=aaaa;
    RETURN total;
END //
DELIMITER ;

-- TRIGGER pago_cliente
DELIMITER //
CREATE TRIGGER pago_cliente AFTER INSERT ON pago
FOR EACH ROW
BEGIN
    INSERT INTO pago(codigo_cliente, accion, fecha_accion) VALUES(NEW.codigo_cliente, 'INSERT', NOW());
END //
DELIMITER ;

-- TRIGGER estado_pedido
DELIMITER //
CREATE TRIGGER estado_pedido BEFORE UPDATE ON pedido
FOR EACH ROW
BEGIN
    IF NEW.fecha_entrega IS NOT NULL THEN SET NEW.estado = 'Entregado';
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
    DECLARE fin_cursor INT DEFAULT 0;
        -- Selecciona vehículos cuyo estado sea 'alquilado'
    DECLARE cur_vehiculos CURSOR FOR
        SELECT matricula, marca, modelo FROM Vehiculo  WHERE estado = 'alquilado';    
    -- Manejador para cuando no haya más filas
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin_cursor = 1;     
    OPEN cur_vehiculos;    
    bucle: LOOP
        FETCH cur_vehiculos INTO v_matricula, v_marca, v_modelo;        
        IF fin_cursor = 1 THEN LEAVE bucle;
        END IF;        
SELECT CONCAT('Vehículo alquilado: ', v_matricula, ' - ', v_marca, ' ', v_modelo) AS Informacion;
        END LOOP bucle;    
    CLOSE cur_vehiculos;
END //
DELIMITER ;

