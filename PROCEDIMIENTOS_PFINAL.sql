-- TRIGGER (2)
DELIMITER //
CREATE TRIGGER before_alquiler_check BEFORE INSERT ON Alquiler FOR EACH ROW
BEGIN
DECLARE estado_v VARCHAR(20);
SELECT estado INTO estado_v FROM Vehiculo WHERE matricula = NEW.matricula;
IF estado_v != 'disponible' THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Vehículo no
disponible';
END IF;
END //
DELIMITER ;


-- FUNCIÓN (2)
DELIMITER //
CREATE FUNCTION TotalGastadoCliente(p_dni VARCHAR(9)) RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN
DECLARE total DECIMAL(10,2);
SELECT SUM(precio_total) INTO total FROM Alquiler WHERE id_cliente = p_dni;
RETURN IFNULL(total, 0);
END //
DELIMITER ;


-- CURSOR (2)
CREATE TABLE AvisosAlquiler (
id_aviso INT AUTO_INCREMENT PRIMARY KEY,
id_alquiler INT,
mensaje VARCHAR(255),
fecha_aviso TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DELIMITER //
CREATE PROCEDURE AlertarAlquileresLargos()
BEGIN
DECLARE done INT DEFAULT FALSE;
DECLARE v_id INT;
DECLARE v_fecha DATE;
DECLARE v_dias INT;
DECLARE cur CURSOR FOR SELECT id_alquiler, fecha_inicio FROM Alquiler WHERE
estado_contrato='activo';
DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
OPEN cur;
read_loop: LOOP
FETCH cur INTO v_id, v_fecha;
IF done THEN LEAVE read_loop; END IF;
SET v_dias = DATEDIFF(CURDATE(), v_fecha);
IF v_dias > 30 THEN INSERT INTO AvisosAlquiler (id_alquiler, mensaje) VALUES (v_id,
CONCAT('Supera 30 días (', v_dias, ')'));
END IF;
END LOOP;
CLOSE cur;
END //
DELIMITER ;


-- PROCEDIMIENTOS (2)

