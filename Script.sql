CREATE DATABASE RuedaFacil; 
USE RuedaFacil; CREATE TABLE Categoria (     id_categoria INT AUTO_INCREMENT PRIMARY KEY,     nombre_categoria VARCHAR(50) NOT NULL UNIQUE 
); 

CREATE TABLE Cliente (     dni VARCHAR(9) PRIMARY KEY,     nombre VARCHAR(100) NOT NULL,     telefono VARCHAR(15) NOT NULL,     email VARCHAR(100) NOT NULL UNIQUE,     direccion VARCHAR(200),     num_carnet VARCHAR(20) NOT NULL UNIQUE 
); 

CREATE TABLE Empleado (     id_empleado INT AUTO_INCREMENT PRIMARY KEY,     nombre VARCHAR(100) NOT NULL,     cargo VARCHAR(50) NOT NULL,     oficina VARCHAR(50),     turno VARCHAR(20),     años_experiencia INT 
); 

CREATE TABLE Vehiculo (     matricula VARCHAR(10) PRIMARY KEY,     marca VARCHAR(50) NOT NULL,     modelo VARCHAR(50) NOT NULL, 
    año INT,     combustible VARCHAR(20),     plazas INT, 
    precio_dia DECIMAL(10,2) NOT NULL,     estado VARCHAR(20) DEFAULT 'disponible',     id_categoria INT, 
    FOREIGN KEY (id_categoria) REFERENCES Categoria(id_categoria) 
); 

CREATE TABLE Alquiler (     id_alquiler INT AUTO_INCREMENT PRIMARY KEY,     fecha_inicio DATE NOT NULL,     fecha_devolucion_prevista DATE NOT NULL,     fecha_devolucion_real DATE,     id_cliente VARCHAR(9) NOT NULL,     id_empleado INT NOT NULL,     matricula VARCHAR(10) NOT NULL,     precio_total DECIMAL(10,2), 
    estado_contrato VARCHAR(20) DEFAULT 'activo', 
    FOREIGN KEY (id_cliente) REFERENCES Cliente(dni),  
    FOREIGN KEY (id_empleado) REFERENCES Empleado(id_empleado), 
    FOREIGN KEY (matricula) REFERENCES Vehiculo(matricula)
  ); 

INSERT INTO Cliente (dni, nombre, telefono, email, direccion, num_carnet) VALUES
('C11122233', 'Carlos Ruiz', '654 321 987', 'carlos.ruiz@example.com', 'Calle Independencia 789', '11122233C'),
('D44455566', 'Luisa Martínez', '655 654 321', 'luisa.martinez@example.com', 'Avenida Libertad 321', '44455566D'),
('E77788899', 'Javier Fernández', '656 987 654', 'javier.fernandez@example.com', 'Calle Principal 654', '77788899E');
INSERT INTO Empleado (nombre, cargo, oficina, turno, años_experiencia) VALUES
('Carlos Martínez', 'Conductor', 'Oficina A', 'mañana', 5),
('María López', 'Administrador', 'Oficina B', 'tarde', 3);

INSERT INTO Vehiculo (matricula, marca, modelo, año, combustible, plazas, precio_dia, estado, id_categoria) VALUES
('V001', 'Toyota', 'Corolla', 2018, 'gasolina', 5, 30.00, 'disponible', (SELECT id_categoria FROM Categoria WHERE nombre_categoria = 'turismo')),
('V002', 'Volkswagen', 'Gol', 2019, 'diesel', 2, 25.00, 'disponible', (SELECT id_categoria FROM Categoria WHERE nombre_categoria = 'furgoneta')),
('V003', 'Honda', 'CBR 600F', 2020, 'gasolina', 1, 50.00, 'disponible', (SELECT id_categoria FROM Categoria WHERE nombre_categoria = 'motocicleta')),
('V004', 'Tesla', 'Model S', 2021, 'eléctrico', 2, 80.00, 'disponible', (SELECT id_categoria FROM Categoria WHERE nombre_categoria = 'eléctrico')),
('V005', 'Jeep', 'Wrangler', 2017, 'gasolina', 4, 35.00, 'disponible', (SELECT id_categoria FROM Categoria WHERE nombre_categoria = 'todoterreno')),
('V006', 'Lamborghini', 'Aventador', 2022, 'gasolina', 2, 150.00, 'disponible', (SELECT id_categoria FROM Categoria WHERE nombre_categoria = 'lujo'));

INSERT INTO Alquiler (fecha_inicio, fecha_devolucion_prevista, id_cliente, id_empleado, matricula, precio_total, estado_contrato) VALUES
('2023-04-01', '2023-04-07', 'A12345678', 1, 'V001', 150.00, 'activo'),
('2023-04-02', '2023-04-08', 'B87654321', 2, 'V002', 100.00, 'activo'),
('2023-04-03', '2023-04-09', 'A12345678', 1, 'V003', 50.00, 'activo'),
('2023-04-04', '2023-04-10', 'B87654321', 2, 'V004', 200.00, 'activo');
