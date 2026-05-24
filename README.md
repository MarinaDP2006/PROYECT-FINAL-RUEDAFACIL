# RuedaFácil - Sistema de Alquiler de Vehículos

## Estructura
RuedaFacil
│
├── src
│   ├── App
│   └── ConexionBD
│
├── DAO
│   ├── AlquilerD
│   ├── CategoriaD
│   ├── ClienteD
│   ├── EmpleadoD
│   └── VehiculoD
│
└── DTO
    ├── Alquilable (interfaz)
    ├── Alquiler
    ├── Cargo (enum de Empleado)
    ├── Categoria
    ├── Cliente
    ├── ClienteNoEncontradoException
    ├── Empleado
    ├── EstadoContrato (enum de Alquiler)
    ├── Vehiculo
    └── VehiculoNoDisponibleException
    
## Descripción
Aplicación para la gestión de una empresa de alquiler de vehículos. Permite administrar clientes, vehículos, categorías, empleados y contratos de alquiler, con cálculo automático de precios e interfaz por consola.

## Requisitos funcionales del sistema
- Gestión de clientes
- Gestión de vehículos y categorías
- Gestión de empleados
- Gestión de alquileres
- Cálculo de precios de alquiler por vehiculo por dia
- Consultas

## ¿Qué se ha usado?
- Java
- MySQL
- JDBC
