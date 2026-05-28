CREATE DATABASE RuedaFacil;
USE RuedaFacil;

CREATE TABLE Categoria (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre_categoria VARCHAR(50) NOT NULL UNIQUE
);
CREATE TABLE Cliente (
    dni VARCHAR(9) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    direccion VARCHAR(200),
    num_carnet VARCHAR(20) NOT NULL UNIQUE
);
CREATE TABLE Empleado (
    id_empleado INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    cargo ENUM (AtencionCliente, PreparadorVehiculo, Mecanico, Inspector, Director),
    oficina VARCHAR(50),
    turno VARCHAR(20), 
    anos_experiencia INT
);
CREATE TABLE Vehiculo (
    matricula VARCHAR(10) PRIMARY KEY,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    ano INT,
    combustible VARCHAR(20),
    plazas INT,
    precio_dia DECIMAL(10,2) NOT NULL,
    estado VARCHAR(20) DEFAULT 'disponible',
    id_categoria INT,
    FOREIGN KEY (id_categoria) REFERENCES Categoria(id_categoria)
);
CREATE TABLE Alquiler (
    id_alquiler INT AUTO_INCREMENT PRIMARY KEY,
    fecha_inicio DATE NOT NULL,
    fecha_devolucion_prevista DATE NOT NULL,
    fecha_devolucion_real DATE,
    id_cliente VARCHAR(9) NOT NULL,
    id_empleado INT NOT NULL,
    matricula VARCHAR(10) NOT NULL,
    precio_total DECIMAL(10,2),
    estado_contrato ENUM (Activo, Completado, Cancelado),
    FOREIGN KEY (id_cliente) REFERENCES Cliente(dni),
    FOREIGN KEY (id_empleado) REFERENCES Empleado(id_empleado),
    FOREIGN KEY (matricula) REFERENCES Vehiculo(matricula)
);

-- INSERT 
INSERT INTO Categoria (nombre_categoria) VALUES
('turismo'),
('furgoneta'),
('motocicleta'),
('electrico'),
('todoterreno'),
('lujo');
INSERT INTO Cliente (dni, nombre, telefono, email, direccion, num_carnet) VALUES
('C11122233', 'Carlos Ruiz', '654321987', 'carlos.ruiz@example.com', 'Calle Independencia 789', '11122233C'),
('D44455566', 'Luisa Martínez', '655654321', 'luisa.martinez@example.com', 'Avenida Libertad 321', '44455566D'),
('E77788899', 'Javier Fernández', '656987654', 'javier.fernandez@example.com', 'Calle Principal 654', '77788899E');
INSERT INTO Empleado (nombre, cargo, oficina, turno, anos_experiencia) VALUES
('Carlos Martínez', 'Conductor', 'Oficina A', 'manana', 5),
('María López', 'Administrador', 'Oficina B', 'tarde', 3);
INSERT INTO Vehiculo (matricula, marca, modelo, ano, combustible, plazas, precio_dia, estado, id_categoria) VALUES
('V001', 'Toyota', 'Corolla', 2018, 'gasolina', 5, 30.00, 'disponible', 1),
('V002', 'Volkswagen', 'Gol', 2019, 'diesel', 2, 25.00, 'disponible', 2),
('V003', 'Honda', 'CBR 600F', 2020, 'gasolina', 1, 50.00, 'disponible', 3),
('V004', 'Tesla', 'Model S', 2021, 'electrico', 2, 80.00, 'disponible', 4),
('V005', 'Jeep', 'Wrangler', 2017, 'gasolina', 4, 35.00, 'disponible', 5),
('V006', 'Lamborghini', 'Aventador', 2022, 'gasolina', 2, 150.00, 'disponible', 6);
INSERT INTO Alquiler (fecha_inicio, fecha_devolucion_prevista, id_cliente, id_empleado, matricula, precio_total, estado_contrato) VALUES
('2023-04-01', '2023-04-07', 'C11122233', 1, 'V001', 150.00, 'activo'),
('2023-04-02', '2023-04-08', 'D44455566', 2, 'V002', 100.00, 'activo'),
('2023-04-03', '2023-04-09', 'C11122233', 1, 'V003', 50.00, 'activo'),
('2023-04-04', '2023-04-10', 'D44455566', 2, 'V004', 200.00, 'activo');  
